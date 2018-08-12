package Models

import org.specs2.mutable.Specification
import org.specs2.specification.BeforeEach

class ThresholdTimeTest extends Specification {
sequential

  "updateThresholdTime" should {
    "properly update threshold time given interval" in {
      ThresholdTime.updateThresholdTime(Time(1500), IntervalTime(300)) should_== ThresholdTime(1200)
    }

  }
}
