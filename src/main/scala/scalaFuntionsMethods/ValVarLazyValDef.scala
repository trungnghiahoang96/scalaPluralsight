package scalaFuntionsMethods

object ValVarLazyValDef extends App {

  val l = Array(1, 2, 3, 4, 5)
  def getLengthMethod(l: Array[Int]): Int = l.length
  val getLengthFunc = (l: Array[Int]) => l.length
  println(getLengthFunc.isInstanceOf[Function1[_, _]])
  println(getLengthFunc.getClass)

  println("method: ", getLengthMethod(l))
  println("function: ", getLengthFunc(l))

}
