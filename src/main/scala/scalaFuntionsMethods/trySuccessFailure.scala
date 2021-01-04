package scalaFuntionsMethods

import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success, Try}

object trySuccessFailure extends App {
  def getLineFromFile_(fileName: String): Try[BufferedSource] = {
    Try(Source.fromFile(fileName))
  }
  //: Try[BufferedSource]
  val trySource: Try[BufferedSource] = getLineFromFile_(
    "src/main/resources/dividendStocks.csv"
  )

  trySource match {
    case Success(trySource) => trySource.getLines.toList.foreach(println)
    case Failure(exception) => println(exception.getMessage)

  }
}
