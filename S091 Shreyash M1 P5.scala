import breeze.linalg._
import scala.util.Random

object practical5 {
  def main (args: Array[String]): Unit = {

    println("Shreyash Kadam SYCS")

    val row = 4
    val col = 4

    val S1 = Array.fill(row * col)(Random.nextInt(10) + 1)
    val matrix = new DenseMatrix( row, col, S1)

    println(s"Random Matrix : \n$matrix")

    val transpose = matrix.t
    println(s"transposed matrix :\n$transpose")

    val S4 = matrix.map(_.toDouble)
    val Determinant = det(S4)
    println(f"determinant : \n$Determinant%.2f")
  }


}
