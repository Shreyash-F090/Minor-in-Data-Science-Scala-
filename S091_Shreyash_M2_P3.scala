import scala.io.Source

object S091_Shreyash_M2_P3 {
  def main(args: Array[String]): Unit = {

    println("Shreyash Kadam S091")

    val stream = getClass.getResourceAsStream("/all_stocks_5yr.csv")

    if (stream == null) {
      println("Error: File not found in resources folder!")
      return
    }

    val file = Source.fromInputStream(stream)

    val prices = file.getLines().drop(1).flatMap { line =>
      val cols = line.split(",")

      if (cols.length >= 7 && cols(6).trim == "AAL")
        cols(4).trim.toDoubleOption
      else
        None
    }.toList

    file.close()

    val minPrice = prices.min
    val maxPrice = prices.max

    val classSize = 10.0

    val start = math.floor(minPrice / classSize) * classSize
    val end = math.ceil(maxPrice / classSize) * classSize

    var cumulative = 0

    println("\nFrequency Distribution")
    println("Price Range\tFrequency\tCumulative")

    var lower = start

    while (lower < end) {

      val upper = lower + classSize

      val frequency = prices.count { price =>
        price >= lower && price < upper
      }

      cumulative = cumulative + frequency

      println(f"$lower%.2f - $upper%.2f\t$frequency%d\t\t$cumulative%d")

      lower = upper
    }
    println(s"Total Records: ${prices.length}")
  }
}


