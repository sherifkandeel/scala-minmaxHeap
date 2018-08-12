package Models

import org.specs2.mutable.Specification

class RawEventTest extends Specification {
sequential

  "apply" should {
    "split over tab and create a RawEvent" in {
      val event = RawEvent("123\t123.23")
      event.time should_== Time(123)
      event.value should_== Value(123.23f)
    }
  }
}
