import breeze.linalg._
import scala.io.Source

object S091_Shreyash_M2_P6 {
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
          open <- cols(1).trim.toDoubleOption
          close <- cols(4).trim.toDoubleOption
        } yield {
          val label = if (close > open) 1.0 else 0.0
          (open, label)
        }
      } else {
        None
      }
    }.take(50).toList

    file.close()

    val x = DenseVector(data.map(_._1).toArray)
    val y = DenseVector(data.map(_._2).toArray)

    var beta0 = 0.0
    var beta1 = 0.0

    val learningRate = 0.0001
    val iterations = 10000

    for (i <- 0 until iterations) {

      var sum0 = 0.0
      var sum1 = 0.0

      for (j <- 0 until x.length) {

        val z = beta0 + beta1 * x(j)
        val probability = 1.0 / (1.0 + math.exp(-z))

        sum0 = sum0 + (probability - y(j))
        sum1 = sum1 + (probability - y(j)) * x(j)
      }

      beta0 = beta0 - learningRate * sum0
      beta1 = beta1 - learningRate * sum1
    }

    println("\nLogistic Regression Model:")
    println(f"Probability = 1 / (1 + e^(-($beta0%.4f + $beta1%.4f * Open)))")

    val openPrice = 40.0

    val z = beta0 + beta1 * openPrice

    val probability =
      1.0 / (1.0 + math.exp(-z))

    val prediction =
      if (probability >= 0.5) 1 else 0

    println(f"\nOpen Price: $openPrice%.2f")
    println(f"Probability of Price Increase: $probability%.4f")

    if (prediction == 1)
      println("Prediction: Price Increase")
    else
      println("Prediction: No Price Increase")

    println(s"\nDataset Size: ${data.length} records")
  }
}