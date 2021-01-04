package scalaFuntionsMethods

object partiallyAppliedFunction extends App {

  println("partially applied function")
  val data = List(1, 2, 4, 3, 5, 6, 7, 8)

  def filterWithThreshold(x: Int, y: Int, z: Int): Boolean = x >= y && x <= z

  val filter47Func = filterWithThreshold(_: Int, _: Int, 7)

  val filteredData = data.filter((value) => filter47Func(value, 2))
  //  println(filteredData)

  filteredData.foreach {
    println
  }
  println("*" * 50)
}
