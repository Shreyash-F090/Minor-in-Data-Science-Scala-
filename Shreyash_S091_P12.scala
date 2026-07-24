import com.github.tototoshi.csv._
import java.io.File

object Shreyash_S091_P12 {
  def main(args: Array[String]): Unit = {

      val reader = CSVReader.open(new File("stock_data.csv"))
      val data = reader.allWithHeaders()
      reader.close()


      val categories = data.map(_("sector")).distinct.sorted

      val newData = data.map { row =>

        val sector = row("sector")

        val oneHot = categories.map { cat =>
          cat -> (if (cat == sector) "1" else "0")
        }.toMap

        (row - "sector") ++ oneHot
      }


      val headers = newData.head.keys.toList
      println(headers.mkString(", "))


      newData.foreach { row =>
        println(headers.map(row).mkString(", "))
      }


      val writer = CSVWriter.open(new File("stock_encoded.csv"))

      writer.writeRow(headers)

      newData.foreach { row =>
        writer.writeRow(headers.map(row))
      }

      writer.close()

      println("One-hot encoded file written to stock_encoded.csv")
    }
  }

