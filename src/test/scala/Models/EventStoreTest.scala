package Models

import org.specs2.mutable.Specification
import org.specs2.specification.{BeforeAfterEach, BeforeAll}

import scala.collection.SortedSet

class EventStoreTest extends Specification with BeforeAfterEach {
sequential

  override def before = {
    val element1 = RawEvent(Time(15000), Value(15.1f))
    val element2 = RawEvent(Time(16000), Value(16.1f))
    val element3 = RawEvent(Time(17000), Value(11.1f))
    EventStore.add(element1)
    EventStore.add(element2)
    EventStore.add(element3)
  }

  override def after = EventStore.clear

  "getLargest" should {
    "get largest element in tree" in {

      EventStore.getLargest.value should_== Value(16.1f)
    }
  }

  "getSum" should {
    "get the sum of elements in store" in {
      "%.1f".format(EventStore.getSum.value).toFloat should_== 42.3f
    }
  }

  "getSmallest" should {
    "getSmallest" in {
      EventStore.getSmallest.value should_== Value(11.1f)
    }
  }

  "updateStore" should {
    "updateStore" in {
      val element4 = RawEvent(Time(19000), Value(10.0f))
      EventStore.updateStore(element4, ThresholdTime(15500))

      EventStore.getSize.value should_==3
    }
  }
}
