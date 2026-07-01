import breeze.linalg.*
import org.apache.commons.math3.distribution.ChiSquaredDistribution

object casestudy {
  def main(args: Array[String]): Unit = {
    
    println("Shreyash Kadam SYCS")

    val points = List(
      120, 118, 115, 112, 110, 108, 105, 103, 101, 99,
      98, 96, 95, 94, 92, 90, 89, 88, 88, 88,
      87, 86, 85, 84, 83, 82, 81, 80, 79, 78,
      77, 76, 75, 74, 73, 72, 71, 70, 69, 68,
      67, 66, 65, 64, 63, 62, 61, 60, 59, 58,
      57, 56, 55, 54, 53, 52, 51, 50, 49, 48
    )

    println(s"\nTeam Points:")
    println(points)

    val mean = points.sum.toDouble / points.length

    val sorted = points.sorted

    val median =
      if (sorted.length % 2 == 0)
        (sorted(sorted.length / 2 - 1) + sorted(sorted.length / 2)).toDouble / 2
      else
        sorted(sorted.length / 2).toDouble

    var mode = points(0)
    var maxCount = 0

    for (i <- points) {

      var count = 0

      for (j <- points) {

        if (i == j) {
          count += 1
        }

      }

      if (count > maxCount) {
        maxCount = count
        mode = i
      }

    }

    val vector = DenseVector(points.map(_.toDouble).toArray)

    val difference = vector.map(x => x - mean)

    val squared = difference.map(x => x * x)

    val variance = sum(squared) / vector.length

    val standardDeviation = math.sqrt(variance)


    println(f"\nMean               : $mean%.2f")
    println(f"Median             : $median%.2f")
    println(s"Mode               : $mode")
    println(f"Variance           : $variance%.2f")
    println(f"Standard Deviation : $standardDeviation%.2f")

  }
}
