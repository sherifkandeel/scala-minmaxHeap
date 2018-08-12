package Models

import org.specs2.mutable.Specification

class EnrichedEventTest extends Specification {
  "EnrichedEventTest.toString" should {
    "return string in the correct format" in {
    val event = EnrichedEvent(Time(1355270621),Value(1.80185f),NumberOfObservations(2),Sum(3.604f),Value(1.80185f),Value(1.80215f))
    event.toString should_== "1355270621  1.80185  2  3.604    1.80185  1.80215"
    }
  }
}
