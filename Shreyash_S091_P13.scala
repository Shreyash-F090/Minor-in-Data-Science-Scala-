import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object Shreyash_S091_P13 {

    def main(args: Array[String]): Unit = {

      val reader = CSVReader.open(new File("stock_data.csv"))
      val data = reader.allWithHeaders()

      reader.close()

      val technology = data.filter(_("sector") == "Technology")
      val finance = data.filter(_("sector") == "Finance")
      val healthcare = data.filter(_("sector") == "Healthcare")

      def extractXY(rows: List[Map[String, String]]) = {
        val x = DenseVector(rows.map(_("open").toDouble).toArray)
        val y = DenseVector(rows.map(_("close").toDouble).toArray)
        (x, y)
      }

      val (xTechnology, yTechnology) = extractXY(technology)
      val (xFinance, yFinance) = extractXY(finance)
      val (xHealthcare, yHealthcare) = extractXY(healthcare)


      val fig = Figure()
      val plt = fig.subplot(0)

      plt.title = "Open Price vs Close Price"
      plt.xlabel = "Open Price"
      plt.ylabel = "Close Price"

      plt += plot(xTechnology, yTechnology, '.', name = "Technology", colorcode = "blue")

      plt += plot(xFinance, yFinance, '.', name = "Finance", colorcode = "green")

      plt += plot(xHealthcare, yHealthcare, '.', name = "Healthcare", colorcode = "red")

      fig.refresh()
    }
  }
