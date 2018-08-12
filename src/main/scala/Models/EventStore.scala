package Models

import com.google.common.collect.MinMaxPriorityQueue

object EventStore {
  val comparator: Ordering[RawEvent] = Ordering.by(_.value.value)
  private val minmax: MinMaxPriorityQueue[RawEvent] =
    MinMaxPriorityQueue.orderedBy(comparator).create()

  def add(elem: RawEvent) = minmax.add(elem)

  def removeIfValueLessThan(threshold: ThresholdTime) =
    minmax.removeIf(_.time.value < threshold.value)

  def getSize = NumberOfObservations(minmax.size)

  def getLargest = minmax.peekLast

  def getSmallest = minmax.peekFirst

  def updateStore(current: RawEvent, threshold: ThresholdTime) = {
    EventStore.add(current)
    EventStore.removeIfValueLessThan(threshold)
  }

  def getSum() = {
    var sum = 0.0f
    val iter = minmax.iterator
    while (iter.hasNext) sum = sum + iter.next.value.value
    Sum(sum)
  }

  def clear = minmax.clear
}
