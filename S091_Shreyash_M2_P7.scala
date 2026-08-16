import breeze.linalg.{DenseVector, euclideanDistance}
import scala.io.Source

object S091_Shreyash_M2_P7 {
    case class DataPoint(features: DenseVector[Double], label: String)

    def main(args: Array[String]): Unit = {

      println("Shreyash Kadam S091")

      val stream = getClass.getResourceAsStream("/all_stocks_5yr.csv")

      if (stream == null) {
        println("Error: File not found in resources folder!")
        return
      }

      val file = Source.fromInputStream(stream)

      val dataset = file.getLines().drop(1).flatMap { line =>
        val cols = line.split(",")

        if (cols.length >= 7 && cols(6).trim == "AAL") {
          for {
            open <- cols(1).trim.toDoubleOption
            close <- cols(4).trim.toDoubleOption
          } yield {
            val label =
              if (close > open) "Increase"
              else "No Increase"

            DataPoint(
              DenseVector(open, close),
              label
            )
          }
        } else {
          None
        }
      }.take(50).toList

      file.close()

      println("\nTraining data points:")
      dataset.foreach { p =>
        println(s"Features: ${p.features}, Label: ${p.label}")
      }

      val newPointFeatures = DenseVector(40.0, 40.5)

      println(s"\nNew data point to classify: $newPointFeatures")

      var minDistance = Double.MaxValue
      var predictedLabel = ""

      for (point <- dataset) {

        val dist =
          euclideanDistance(newPointFeatures, point.features)

        if (dist < minDistance) {
          minDistance = dist
          predictedLabel = point.label
        }
      }

      println("\nClassification Result:")
      println(s"Nearest neighbor distance: $minDistance")
      println(s"Predicted label: $predictedLabel")
    }
  }


