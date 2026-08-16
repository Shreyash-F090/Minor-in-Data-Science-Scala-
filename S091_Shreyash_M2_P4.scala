import scala.io.Source

object S091_Shreyash_M2_P4 {
  def main(args: Array[String]): Unit = {

    println("Shreyash Kadam S091")

    val stream = getClass.getResourceAsStream("/all_stocks_5yr.csv")

    if (stream == null) {
      println("Error: File not found in resources folder!")
      return
    }

    val file = Source.fromInputStream(stream)

    val data = file.getLines().drop(1).flatMap { line =>
      val cols = line.split(",")

      if (cols.length >= 7 && cols(6).trim == "AAL") {
        for {
          
          close <- cols(4).trim.toDoubleOption
        } yield (cols(0), close)
      } else {
        None
      }
    }.toList

    file.close()

    val sortedData = data.sortBy(_._2).reverse

    val top5 = sortedData.take(5)

    println("\nTop 5 AAL Closing Prices:")
    top5.foreach { row =>
      println(s"Date: ${row._1}  Close: ${row._2}")
    }
  }
}




