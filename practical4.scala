import breeze.linalg._
object practical4 {
    def main(args: Array[String]): Unit = {
      val S1 = DenseVector(1.0, 2.0, 3.0, 4.0)
      val S2 = DenseVector(5.0, 6.0, 7.0, 8.0)

      println(s"Vector 1 : $S1")
      println(s"Vector 2 : $S2")

      val sum = breeze.linalg.sum(S1)
      println(f"Sum of S1 : $sum%.2f")

      val mean = breeze.stats.mean(S2)
      println(f"Mean of S2 : $mean%.2f")

      val S3 = S1 dot S2
      println(f"Dot product of S1 and S1 : $S3%.3f")

    }

  }


