package Models

case class EnrichedEvent(time: Time, value: Value, observationsCount: NumberOfObservations, sum: Sum, minValue: Value, maxValue: Value) {
  override def toString: String =
    f"${this.time.value}%-12s" +
      f"${this.value.value}%-9s" +
      f"${this.observationsCount.value}%-3s" +
      f"${"%.5f".format(this.sum.value).toFloat}%-9s" +
      f"${this.minValue.value}%-9s" +
      f"${this.maxValue.value}%-7s"
}
