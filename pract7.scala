package practical7
import breeze.linalg._

object pract7 {
  def main(args: Array[String]): Unit = {
    println("Shreyash Kadam S090")

    val S1 = DenseMatrix((1.0, 2.0),(3.0, 4.0))
    println(s"\nMatrix 1:\n$S1")
    val S2 = DenseMatrix((5.0, 6.0),(7.0, 8.0))
    println(s"\nMatrix 2:\n$S2")


    val S4 = S1 +  S2
    println(s"\nAddition of two Same Size Matrix:\n$S4")

    val S5 = S2 - S1
    println(s"\nSubstraction of two Same Size Matrix:\n$S5")

    val S6 = S1 * S2
    println(s"\nMultiplication of Two Same Size Matrix:\n$S6")

    val S7 = S2 / S1
    print(s"\nDivision of Two Same Size Matrix:\n$S7")

  }



}
