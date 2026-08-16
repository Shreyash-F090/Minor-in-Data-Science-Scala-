import breeze.linalg._
import scala.io.Source

object S091_Shreyash_M2_P8 {
    def main(args: Array[String]): Unit = {

      println("Shreyash Kadam S091")

      val stream = getClass.getResourceAsStream("/all_stocks_5yr.csv")

      if (stream == null) {
        println("Error: File not found in resources folder!")
        return
      }

      val file = Source.fromInputStream(stream)

      val values = file.getLines().drop(1).flatMap { line =>
        val cols = line.split(",")

        if (cols.length >= 7 && cols(6).trim == "AAL") {
          for {
            open <- cols(1).trim.toDoubleOption
            close <- cols(4).trim.toDoubleOption
          } yield (open, close)
        } else {
          None
        }
      }.take(50).toList

      file.close()

      val data = DenseMatrix(
        values.map { case (open, close) =>
          Array(open, close)
        }: _*
      )

      val k = 2
      val numFeatures = 2
      val maxIterations = 100

      println(s"\nDataset with ${data.rows} samples and ${data.cols} features.")

      var centroids = DenseMatrix.zeros[Double](k, numFeatures)

      centroids(0, ::) := data(0, ::)
      centroids(1, ::) := data(25, ::)

      println(s"\nInitial centroids:\n$centroids")

      var assignments = DenseVector.zeros[Int](data.rows)
      var previousAssignments = DenseVector.fill[Int](data.rows)(-1)

      var iteration = 0
      var converged = false

      while (iteration < maxIterations && !converged) {

        println(s"\n--- Iteration ${iteration + 1} ---")

        for (i <- 0 until data.rows) {

          val point = data(i, ::).t

          var minDistance = Double.MaxValue
          var closestCentroidIndex = -1

          for (j <- 0 until k) {

            val centroid = centroids(j, ::).t

            val dist = euclideanDistance(point, centroid)

            if (dist < minDistance) {
              minDistance = dist
              closestCentroidIndex = j
            }
          }

          assignments(i) = closestCentroidIndex
        }

        if (assignments == previousAssignments) {
          converged = true
        } else {
          previousAssignments = assignments.copy
        }

        val newCentroids = DenseMatrix.zeros[Double](k, numFeatures)
        val clusterCounts = DenseVector.zeros[Int](k)

        for (i <- 0 until data.rows) {

          val clusterId = assignments(i)

          newCentroids(clusterId, ::) :=
            newCentroids(clusterId, ::) + data(i, ::)

          clusterCounts(clusterId) += 1
        }

        for (i <- 0 until k) {

          if (clusterCounts(i) > 0) {
            newCentroids(i, ::) :=
              newCentroids(i, ::) / clusterCounts(i).toDouble
          }
        }

        centroids = newCentroids

        println(s"Updated centroids:\n$centroids")

        iteration += 1
      }

      println("\n--- Final Results ---")

      println(s"K-means algorithm converged in $iteration iterations.")

      println(s"\nFinal centroids:\n$centroids")

      println(s"\nFinal cluster assignments:\n$assignments")
    }
  }

