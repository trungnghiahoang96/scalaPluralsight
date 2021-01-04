package scalaFuntionsMethods
import scala.io.StdIn
import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success, Try}

object trySuccessFailureComplexExp extends App {

  def readCSVFile(fileName: String): Seq[DividendStocks] = {
    val source = Source.fromFile(fileName)
    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    } yield DividendStocks(cols(0), cols(1), cols(2).toFloat, cols(3).toFloat)

  }
  val records = readCSVFile("src/main/resources/dividendStocks.csv")
//  records.foreach(println)
  @scala.annotation.tailrec
  def calculatingDivYield(): Try[Double] = {

    val ticker = StdIn.readLine("Enter your ticker:  ")
    val currentPrice: Try[Double] = Try(
      StdIn.readLine("Enter your current price: ").toDouble
    )

    val faceValue: Try[Double] = Try(
      records.filter(_.ticker == ticker).map(_.face_value).head.toDouble
    )
    val dividendRate: Try[Double] = Try(
      records.filter(_.ticker == ticker).map(_.dividend).head * 0.01
    )

    val dividendPerShare: Try[Double] =
      faceValue.flatMap(fv => dividendRate.map(divRate => fv * divRate))
    val dividendYield: Try[Double] = dividendPerShare.flatMap(
      divPerShare =>
        currentPrice.map(currPrice => divPerShare / currPrice * 100)
    )

    dividendYield match {
      case Success(dividendYield) =>
        println(s"With ticker $ticker, divRate: ${dividendYield}%")
        Success(dividendYield)

      case Failure(exception) =>
        println(exception.getMessage)
        calculatingDivYield()

    }

  }
  calculatingDivYield()
}
