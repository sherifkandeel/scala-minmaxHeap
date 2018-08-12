import Models._

class Printer {
  def printHeader() = {
    printLine("   Time      Value  N_O Roll_Sum Min_Value Max_Value")
    printLine("---------------------------------------------------")
  }

  def printLine(s: String): Unit = println(s)
}
