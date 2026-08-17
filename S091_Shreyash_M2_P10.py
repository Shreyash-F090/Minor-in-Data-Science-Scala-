from pyspark.sql import SparkSession

spark = SparkSession.builder \
    .appName("FilterRows") \
    .master("local[*]") \
    .getOrCreate()

data = spark.read.csv("stocks.csv", header=True, inferSchema=True)

threshold = 40.0

filtered_data = data.filter(data["close"] > threshold)

print("\nRows where Close Price > 40:")
filtered_data.show()

print("Total Rows:", filtered_data.count())

spark.stop()