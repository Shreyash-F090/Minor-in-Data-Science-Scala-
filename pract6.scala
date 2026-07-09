package Practical6
import breeze.linalg._
object pract6 {
  def main (Args: Array[String]):  Unit = {
    println("Shreyash Kadam S090")

    val S1 = DenseMatrix(
      (90, 94, 98, 102),
      (106, 110, 114, 118),
      (122, 126, 130, 134),
      (138, 142, 146, 150)
    )

    println(s"Original Matrix: \n$S1")

    val S2 = S1(1  to  3, 1  to  3)
    println(s"\nSlicing of matrix: \n$S2")

    val S3 = sum(S2(*, ::))
    println(s"\nSum of Rows: $S3")

    val S4 = sum(S2(::, *))
    println(s"\nSum of Columns: $S4")

  }
}
