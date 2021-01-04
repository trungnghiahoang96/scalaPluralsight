package scalaFuntionsMethods
import scala.io.{BufferedSource, Source}
import java.io.{FileNotFoundException, IOException}

object tryCatchFinally extends App {
  def getLineFromFile(fileName: String): List[String] = {
    var sourcePath: BufferedSource = null
    try {
      val sourcePath = Source.fromFile(fileName)
      println("done read file.")
      sourcePath.getLines.toList

    } catch {
      case ex: FileNotFoundException => {
        println("File not found!")
        List()
      }
      case ex: IOException => {
        println("I/O exception")
        List()
      }
    } finally {
      println("Close file if has been open")
      if (sourcePath != null) {
        println("Closing file....")
        sourcePath.close()
      }
      List("nghia", "dataEngineer", "airFlow")
    }

  }
  val fileName = "src/main/resources/dividendStocks.csv"
  getLineFromFile(fileName).foreach(println)

  println("end code!")

}
