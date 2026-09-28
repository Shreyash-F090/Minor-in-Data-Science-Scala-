import java.time.LocalDate
import scala.util.Random

case class NiftyData(date: LocalDate, value: Double)

object S091_Shreyash_M2_P14 {

  def main(args: Array[String]): Unit = {

    println("Shreyash Kadam S091")

    val startDate = LocalDate.of(2026, 9, 1)
    val random = new Random(42)

    var currentValue = 25000.0

    val niftyData = (0 until 30).map { day =>

      val date = startDate.plusDays(day.toLong)

      val dailyChange = random.nextDouble() * 400 - 200

      currentValue = currentValue + dailyChange

      NiftyData(date, currentValue)

    }.toList

    println("\n30-Day NIFTY 50 Values:")

    niftyData.foreach { data =>
      println(f"${data.date} : ${data.value}%.2f")
    }

    val averageValue =
      niftyData.map(_.value).sum / niftyData.length

    val highestValue =
      niftyData.maxBy(_.value)

    val lowestValue =
      niftyData.minBy(_.value)

    println("\nTime Series Analysis:")

    println(f"Average NIFTY Value: $averageValue%.2f")

    println(
      f"Highest NIFTY Value: ${highestValue.value}%.2f on ${highestValue.date}"
    )

    println(
      f"Lowest NIFTY Value: ${lowestValue.value}%.2f on ${lowestValue.date}"
    )

    println("\n7-Day Moving Average:")

    val movingAverage =
      niftyData.sliding(7).map { window =>

        val avg =
          window.map(_.value).sum / window.size

        (window.last.date, avg)

      }.toList

    movingAverage.foreach { case (date, avg) =>
      println(f"$date : $avg%.2f")
    }

    val firstValue = niftyData.head.value
    val lastValue = niftyData.last.value

    println("\nTrend Analysis:")

    if (lastValue > firstValue)
      println("NIFTY shows an increasing trend.")
    else if (lastValue < firstValue)
      println("NIFTY shows a decreasing trend.")
    else
      println("NIFTY remains almost stable.")
  }
}