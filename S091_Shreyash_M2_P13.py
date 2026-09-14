from pyspark.sql import SparkSession
from pyspark.sql.functions import col, when
from pyspark.ml import Pipeline
from pyspark.ml.feature import VectorAssembler
from pyspark.ml.classification import LogisticRegression
from pyspark.ml.evaluation import BinaryClassificationEvaluator

spark = SparkSession.builder \
    .appName("SparkMLlibClassification") \
    .master("local[*]") \
    .getOrCreate()

print("==============================================")
print("       SPARK MLlib CLASSIFICATION")
print("       Shreyash Kadam - S091")
print("==============================================")

data = spark.read.csv(
    "all_stocks_5yr.csv",
    header=True,
    inferSchema=True
)

print("\nOriginal Dataset:")
data.show(10)

data = data.select(
    "open",
    "high",
    "low",
    "close",
    "volume"
)

data = data.dropna()

data = data.withColumn(
    "label",
    when(col("close") > col("open"), 1.0)
    .otherwise(0.0)
)

print("\nDataset with Label:")
data.show(10)

print("\nClass Distribution:")
data.groupBy("label").count().show()

features = [
    "open",
    "high",
    "low",
    "volume"
]

assembler = VectorAssembler(
    inputCols=features,
    outputCol="features"
)

lr = LogisticRegression(
    featuresCol="features",
    labelCol="label",
    predictionCol="prediction"
)

pipeline = Pipeline(
    stages=[
        assembler,
        lr
    ]
)

train_data, test_data = data.randomSplit(
    [0.8, 0.2],
    seed=42
)

print("\nTraining Data:", train_data.count())
print("Testing Data :", test_data.count())

model = pipeline.fit(train_data)

predictions = model.transform(test_data)

print("\nPredictions:")
predictions.select(
    "open",
    "high",
    "low",
    "volume",
    "label",
    "prediction"
).show(20)

true_positive = predictions.filter(
    (col("label") == 1.0) &
    (col("prediction") == 1.0)
).count()

true_negative = predictions.filter(
    (col("label") == 0.0) &
    (col("prediction") == 0.0)
).count()

false_positive = predictions.filter(
    (col("label") == 0.0) &
    (col("prediction") == 1.0)
).count()

false_negative = predictions.filter(
    (col("label") == 1.0) &
    (col("prediction") == 0.0)
).count()

print("\n==============================================")
print("              CONFUSION MATRIX")
print("==============================================")

print("                 Predicted")
print("                 0       1")
print("Actual 0        ", true_negative, "   ", false_positive)
print("Actual 1        ", false_negative, "   ", true_positive)

total = (
        true_positive +
        true_negative +
        false_positive +
        false_negative
)

accuracy = (
    (true_positive + true_negative) / total
    if total > 0 else 0
)

sensitivity = (
    true_positive / (true_positive + false_negative)
    if (true_positive + false_negative) > 0
    else 0
)

precision = (
    true_positive / (true_positive + false_positive)
    if (true_positive + false_positive) > 0
    else 0
)

f1_score = (
    2 * precision * sensitivity /
    (precision + sensitivity)
    if (precision + sensitivity) > 0
    else 0
)

auc_evaluator = BinaryClassificationEvaluator(
    labelCol="label",
    rawPredictionCol="rawPrediction",
    metricName="areaUnderROC"
)

auc = auc_evaluator.evaluate(predictions)

print("\n==============================================")
print("          CLASSIFICATION RESULTS")
print("==============================================")

print(f"Accuracy    : {accuracy:.4f}")
print(f"Sensitivity : {sensitivity:.4f}")
print(f"Precision   : {precision:.4f}")
print(f"F1 Score    : {f1_score:.4f}")
print(f"AUC         : {auc:.4f}")

print("\n==============================================")
print("                 END")
print("==============================================")

spark.stop()