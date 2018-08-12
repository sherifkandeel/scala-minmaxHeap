package Models

case class Value(value: Float) extends AnyVal
case class Time(value: Long) extends AnyVal
case class NumberOfObservations(value: Int) extends AnyVal
case class Sum(value: Float) extends AnyVal
case class IntervalTime(value: Long) extends AnyVal

case class ThresholdTime(value: Long) extends AnyVal
object ThresholdTime {
  def updateThresholdTime(t: Time, interval: IntervalTime) = ThresholdTime(t.value - interval.value)
}





