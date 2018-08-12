import Models._

class Executor(printer: Printer, fileIterator: Iterator[String]) {
  def execute(intervalTime: IntervalTime) = {

    printer.printHeader
    fileIterator.foreach { dataLine =>
      val current = RawEvent(dataLine)

      val earliestTime = ThresholdTime.updateThresholdTime(current.time, intervalTime)

      EventStore.updateStore(current, earliestTime)

      val output = EnrichedEvent(current.time,
                                 current.value,
                                 EventStore.getSize,
                                 EventStore.getSum,
                                 EventStore.getSmallest.value,
                                 EventStore.getLargest.value)
      printer.printLine(output.toString)
    }
  }

}
