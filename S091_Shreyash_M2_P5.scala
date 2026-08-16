import breeze.linalg._
import scala.io.Source

object S091_Shreyash_M2_P5 {
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
          x <- cols(1).trim.toDoubleOption
          y <- cols(4).trim.toDoubleOption
        } yield (x, y)
      } else {
        None
      }
    }.take(50).toList

    file.close()

    val x = DenseVector(data.map(_._1).toArray)
    val y = DenseVector(data.map(_._2).toArray)

    val X = DenseMatrix.horzcat(
      DenseVector.ones[Double](x.length).toDenseMatrix.t,
      x.toDenseMatrix.t
    )

    val coefficients =
      inv(X.t * X) * X.t * y

    val intercept = coefficients(0)
    val slope = coefficients(1)

    println("\nLinear Regression Model:")
    println(f"Close = $intercept%.4f + ($slope%.4f * Open)")

    val openPrice = 40.0

    val predictedClose =
      intercept + slope * openPrice

    println(f"\nOpen Price: $openPrice%.2f")
    println(f"Predicted Close Price: $predictedClose%.2f")

    println(s"\nDataset Size: ${data.length} records")
  }
}

