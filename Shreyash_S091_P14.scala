import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object Shreyash_S091_P14 {

    def main(args: Array[String]): Unit = {

      val reader = CSVReader.open(new File("stock_data.csv"))
      val data = reader.allWithHeaders()

      reader.close()

      val closePrices =
        DenseVector(data.map(_("close").toDouble).toArray)


      val fig = Figure("Histogram of Stock Closing Prices")

      val binSizes = List(5, 10, 20)

      for ((bins, idx) <- binSizes.zipWithIndex) {

        val plt = fig.subplot(1, binSizes.length, idx)

        plt += hist(closePrices, bins)

        plt.title = s"Histogram with $bins bins"
        plt.xlabel = "Closing Price"
        plt.ylabel = "Frequency"
      }

      fig.refresh()
    }
  }


