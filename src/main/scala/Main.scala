import Models._

object Main extends App {
  if (args.length == 0) {
    println("You must enter the window size in seconds")
    sys.exit(1)
  }

  val intervalTime = IntervalTime(args(0).toLong)
  val fileName = "data.txt"
  val printer = new Printer()
  val lineIterator = scala.io.Source.fromFile(fileName).getLines()
  val executor = new Executor(printer, lineIterator)

  executor.execute(intervalTime)
}
