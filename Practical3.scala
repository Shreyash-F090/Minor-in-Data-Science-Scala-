import scala.util.Random
import breeze.linalg._

object Practical3 {
  def main(args: Array[String]): Unit = {
    
    println("Shreyash Kadam SYCS")

     val data = DenseVector.fill(10)((Random.nextInt(10) + 1).toDouble)
    println(s"Random Dataset : $data")

    val mean = sum(data) / data.length

    def subtractMean(x: Double): Double = {
      x - mean
    }

    val difference = data.map(subtractMean)

    def square(x: Double): Double = {
      x * x
    }

    val squared = difference.map(square)

    val variance = sum(squared) / data.length

    val stdDeviation = math.sqrt(variance)

    println(f"Mean : $mean%.2f")
    println(f"Variance : $variance%.2f")
    println(f"Standared Deviation : $stdDeviation%.2f")
  }
}
