from pyspark.sql import SparkSession

spark = SparkSession.builder \
    .appName("WordCount") \
    .master("local[*]") \
    .getOrCreate()

text_file = spark.sparkContext.textFile("input.txt")

words = text_file.flatMap(lambda line: line.split())

word_pairs = words.map(lambda word: (word, 1))

word_counts = word_pairs.reduceByKey(lambda a, b: a + b)

print("\nShryeash Kadam S091")
print("\nWord Frequency:")


for word, count in word_counts.collect():
    print(word, ":", count)

spark.stop()