package Models

case class RawEvent(time: Time, value: Value)

object RawEvent {
  def apply(line: String): RawEvent = {
    val values = line.split("\t")
    RawEvent(Time(values(0).trim.toLong), Value(values(1).trim.toFloat))
  }
}
