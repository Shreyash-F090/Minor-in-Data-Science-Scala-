from pyspark.sql import SparkSession

spark = SparkSession.builder \
    .appName("JoinCSVFiles") \
    .master("local[*]") \
    .getOrCreate()

# Read stock CSV
stock_data = spark.read.csv(
    "stocks.csv",
    header=True,
    inferSchema=True
)

# Read company information CSV
company_data = spark.read.csv(
    "company_info.csv",
    header=True,
    inferSchema=True
)

# Join using common column
joined_data = stock_data.join(company_data, "symbol")

print("\nJoined Data:")
joined_data.show()

# Write joined data to CSV file
output_path = "joined_output.csv"

joined_data.toPandas().to_csv(output_path, index=False)

print("\nJoined data written successfully to joined_output.csv")

spark.stop()