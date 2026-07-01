
object Practical2 {
  def main(args: Array[String]): Unit = {

    println("Shreyash Kadam SYCS")

    val numbers = List(2, 4, 6, 8, 10, 6, 4, 2, 6, 8)

    println(s"Numbers: $numbers")

    val mean = numbers.sum.toDouble / numbers.length

    val sorted = numbers.sorted

    val median =
      if (sorted.length % 2 == 0)
        (sorted(sorted.length / 2 - 1) + sorted(sorted.length / 2)).toDouble / 2
      else
        sorted(sorted.length / 2).toDouble

    var mode = numbers(0)
    var maxCount = 0

    for (i <- numbers) {
      var count = 0

      for (j <- numbers) {
        if (i == j)
          count += 1
      }

      if (count > maxCount) {
        maxCount = count
        mode = i
      }
    }

    println(f"Mean: $mean%.2f")
    println(f"Median: $median%.2f")
    println(s"Mode: $mode")
  }
}

