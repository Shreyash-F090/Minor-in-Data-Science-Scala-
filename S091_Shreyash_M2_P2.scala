import scala.io.Source

object S091_Shreyash_M2_P2 {
    def main(args: Array[String]): Unit = {

      println("Shreyash Kadam S091")

      val stream = getClass.getResourceAsStream("/all_stocks_5yr.csv")

      if (stream == null) {
        println("File not found!")
        return
      }

      val file = Source.fromInputStream(stream)

      val rows = file.getLines().drop(1).flatMap { line =>

        val cols = line.split(",")

        if (cols.length >= 7 && cols(6) == "AAL") {

          cols(4).trim.toDoubleOption

        } else None

      }.toList

      file.close()

      val window = 5

      println("\nSimple Moving Average (SMA)\n")

      for (i <- window - 1 until rows.length) {

        val sma =
          rows.slice(i - window + 1, i + 1).sum / window

        println(f"Day ${i + 1}%4d : $sma%.2f")

      }

      println("\nWeighted Moving Average (WMA)\n")

      val weights = List(1, 2, 3, 4, 5)

      val weightSum = weights.sum

      for (i <- window - 1 until rows.length) {

        val values = rows.slice(i - window + 1, i + 1)

        val wma =
          values.zip(weights)
            .map(x => x._1 * x._2)
            .sum / weightSum

        println(f"Day ${i + 1}%4d : $wma%.2f")

      }

      println("\nExponential Moving Average (EMA)\n")

      val alpha = 2.0 / (window + 1)

      var ema = rows.head

      println(f"Day 1 : $ema%.2f")

      for (i <- 1 until rows.length) {

        ema = alpha * rows(i) + (1 - alpha) * ema

        println(f"Day ${i + 1}%4d : $ema%.2f")

      }

    }

  }