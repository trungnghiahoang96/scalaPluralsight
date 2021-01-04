package scalaFuntionsMethods

object higherOrderFunction extends App {
  val readFinanceDataHOF = () => {
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

  val data = readFinanceDataHOF()
//  println(data.getClass)
  //  for (record <- data) println(record)
  val extractHigh = (records: Vector[StockMarket]) => {
    for (record <- records) yield (record.ticker, record.high)
  }

  val extractOpen = (records: Vector[StockMarket]) => {
    for (record <- records) yield (record.ticker, record.open)
  }

  val extractClose = (records: Vector[StockMarket]) => {
    for (record <- records) yield (record.ticker, record.close)
  }
  // an Higher order function that take each func + data to execute it
  val extractInfo =
    (records: Vector[StockMarket],
     extractFunc: Vector[StockMarket] => Vector[(String, Float)]) => {
      extractFunc(records)
    }
  //another func that more general and return function:
  val generalExtractInfo =
    (kindOfInfo: String, records: Vector[StockMarket]) => {
      if (kindOfInfo == "high") { () =>
        for (record <- records) yield (record.ticker, record.high)
      } else if (kindOfInfo == "open") { () =>
        for (record <- records) yield (record.ticker, record.open)
      } else if (kindOfInfo == "close") { () =>
        for (record <- records) yield (record.ticker, record.close)
      } else { () =>
        for (record <- records)
          yield (record.ticker, record.close - record.open)
      }
    }

//  println(extractHigh(data).getClass)

  println("High: ", extractInfo(data, extractHigh))
  println("Open: ", extractInfo(data, extractOpen))
  println("Close: ", extractInfo(data, extractClose))

  val high = generalExtractInfo("high", data)
  println("high: " + high())

}
