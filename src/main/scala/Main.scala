case class DataLine(time: Long, value: Float)
object DataLine {
  def apply(line: String): DataLine = {
    val values = line.split("\t")
    DataLine(values(0).trim.toLong, values(1).trim.toFloat)
  }
}
case class GeneratedDataLine(time: Long,
                             value: Float,
                             observationsCount: Int,
                             minValue: Float,
                             maxValue: Float)

object Main extends App {
  val lineIterator = scala.io.Source.fromFile("test.txt").getLines()

  val intervalTime = 60

  var map = scala.collection.SortedSet[DataLine]()(Ordering.by(_.value))

  var treeMap = scala.collection.mutable.SortedMap[Float, DataLine]()

  var rollingWindow = List[DataLine]()
  while (lineIterator.hasNext) {

    val current = DataLine(lineIterator.next())

    val earliestTimePoint = current.time - intervalTime

    rollingWindow = rollingWindow :+ current
    map = map + current

    rollingWindow = rollingWindow.dropWhile(_.time < earliestTimePoint)
    map = map.filterNot(_.time < earliestTimePoint)

    val sum = rollingWindow.foldLeft(0:Float)((x, dl) => x + dl.value)
    val observations = rollingWindow.length

    println(f"${current.time} ${current.value} $observations $sum%2.5f ${map.firstKey.value} ${map.lastKey.value}")
  }

}
