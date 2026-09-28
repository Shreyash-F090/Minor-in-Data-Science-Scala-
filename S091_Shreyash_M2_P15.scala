object S091_Shreyash_M2_P15 {

  def main(args: Array[String]): Unit = {

    println("Shreyash Kadam S091")

    val data = List(1, 2, 3, 4, 5)

    val degree = 3

    val polynomialFeatures = data.flatMap { x =>
      (1 to degree).map(d => math.pow(x, d).toInt)
    }

    println("\nOriginal Data:")
    println(data)

    println("\nPolynomial Features:")
    println(polynomialFeatures)
  }
}