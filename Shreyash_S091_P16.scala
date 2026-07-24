import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object Shreyash_S091_P16 {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("stock_data.csv"))
    val data = reader.allWithHeaders()

    reader.close()
    val x = DenseVector(
      data.map(_("day").toDouble).toArray
    )

    val y = DenseVector(
      data.map(_("close").toDouble).toArray
    )

    val fig = Figure("Stock Closing Price - Line + Scatter Plot")
    val plt = fig.subplot(0)


    plt += plot(x, y, name = "Close Price Line", colorcode = "blue")

    plt += plot(x, y, '.', name = "Close Price Points", colorcode = "red")

    plt.xlabel = "Time (Days)"
    plt.ylabel = "Close Price"
    plt.title = "Stock Closing Price - Line + Scatter Plot"

    fig.refresh()
  }
}