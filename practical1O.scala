package practical1O

import com.github.tototoshi.csv._
import java.io.File

object practical1O {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("all_stocks_5yr.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val threshold = 300.0

    val filteredRows = data.filter { row =>
      row.get("close").exists(value => value.toDoubleOption.exists(_ > threshold))
    }

    println(s"\nTotal Rows with Close Price > $threshold: ${filteredRows.length}\n")

    filteredRows.foreach { row =>
      println(row.values.mkString(", "))
    }
  }
}