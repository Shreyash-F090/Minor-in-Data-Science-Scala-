import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object Shreyash_S091_P15 {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("stock_data.csv"))
    val data = reader.allWithHeaders()

    reader.close()

    // Extract day and close price
    val x = DenseVector(
      data.map(_("day").toDouble).toArray
    )

    val y = DenseVector(
      data.map(_("close").toDouble).toArray
    )


    val fig = Figure("Stock Closing Price Trend")
    val plt = fig.subplot(0)

    plt += plot(
      x,
      y,
      name = "Close Price",
      colorcode = "blue"
    )

    plt.xlabel = "Time (Days)"
    plt.ylabel = "Close Price"
    plt.title = "Stock Closing Price Over Time"

    fig.refresh()
  }
}