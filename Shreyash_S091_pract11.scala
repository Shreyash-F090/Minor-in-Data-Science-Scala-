import scala.collection.LinearSeq
import scala.io.Source

object Shreyash_S091_pract_11 {
  def main(args: Array[String]): Unit = {
    val filename = "Shreyash.txt"

    val lines = Source.fromFile(filename).getLines().toList

    val words = lines
      .flatMap(_.toLowerCase.split("\\W+"))
      .filter(_.nonEmpty)

    val wordCount = words.groupBy(identity).view.mapValues(_.size).toMap

    println("Word Frequencies:")
    wordCount.toSeq.sortBy(-_._2).foreach { case (word, count) =>
      println(f"$word%-15s -> $count")

    }

  }

}
