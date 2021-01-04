package scalaFuntionsMethods

object partialFunction extends App {

  val divide64By = new PartialFunction[Int, Int] {
    //
    def apply(x: Int): Int = 64 / x

    def isDefinedAt(x: Int): Boolean = x != 0

    //     or using this implement
    //    case x: Int if x != 0 => 64 / x

  }
  println("Defined at 11: " + divide64By.isDefinedAt(11))
  println("Defined at 0: " + divide64By.isDefinedAt(0))

  println("Divide 64 by 3: " + (if (divide64By.isDefinedAt(3)) divide64By(3)))
  println("*" * 100)

  val readFinanceData_ = () => {
    val source = io.Source.fromFile("src/main/resources/stockMarketData.csv")
    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    } yield
      StockMarket(
        cols(0),
        cols(1).toFloat,
        cols(2).toFloat,
        cols(3).toFloat,
        cols(4).toFloat,
        cols(5)
      )
  }

  val data = readFinanceData_()

  val printStockRecords: PartialFunction[String, Unit] =
    new PartialFunction[String, Unit] {

      val recordedTickers = List("MSFT", "GOOG", "TTM", "TM", "DB", "BNS")

      def apply(ticker: String): Unit = {
        for (lines <- data.filter(_.ticker == ticker)) {

          println(
            s"Date: ${lines.date} Ticker: ${lines.ticker} Close: ${lines.close} "
          )
        }
      }

      def isDefinedAt(ticker: String): Boolean =
        recordedTickers.contains(ticker)
    }

  if (printStockRecords.isDefinedAt("GOOG")) printStockRecords("GOOG")

  val printStockRecordsWithCase: PartialFunction[String, Unit] = {
    case ticker: String
        if (List("MSFT", "GOOG", "TTM", "TM", "DB", "BNS")
          .contains(ticker)) => {
      for (lines <- data.filter(_.ticker == ticker)) {

        println(
          s"Date: ${lines.date} Ticker: ${lines.ticker} Close: ${lines.close} "
        )
      }

    }

  }
  List("MSFT", "GOOG", "TTM") map { printStockRecordsWithCase }
}
