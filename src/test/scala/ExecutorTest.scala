import Models._
import org.scalamock.specs2.MockContext
import org.specs2.mutable.Specification

class ExecutorTest extends Specification {
sequential

  "ExecutorTest is an E2E test" should {
    "execute what's in test.txt" in new MockContext {
      val actualPrinter = new Printer()
      val printerMock = mock[Printer]
      var output = ""
      (printerMock.printHeader _)
        .expects()
        .onCall { _ =>
          output = output + "   Time      Value  N_O Roll_Sum Min_Value Max_Value" +
            "\n---------------------------------------------------\n"
        }
        .once

      (printerMock.printLine _)
        .expects(*)
        .onCall { x: String =>
          output = output + x + "\n"
        }
        .anyNumberOfTimes

      val lineIterator = scala.io.Source.fromFile("test.txt").getLines()
      val executor = new Executor(printerMock, lineIterator)

      executor.execute(IntervalTime(60))

      output should_==
"""   Time      Value  N_O Roll_Sum Min_Value Max_Value
---------------------------------------------------
1355270609  1.80215  1  1.80215  1.80215  1.80215
1355270621  1.80185  2  3.604    1.80185  1.80215
1355270646  1.80195  3  5.40595  1.80185  1.80215
1355270702  1.80225  2  3.6042   1.80195  1.80225
1355270702  1.80215  3  5.40635  1.80195  1.80225
1355270829  1.80235  1  1.80235  1.80235  1.80235
1355270854  1.80205  2  3.6044   1.80205  1.80235
1355270868  1.80225  3  5.40665  1.80205  1.80235
1355271000  1.80245  1  1.80245  1.80245  1.80245
1355271023  1.80285  2  3.6053   1.80245  1.80285
1355271024  1.80275  3  5.40805  1.80245  1.80285
1355271026  1.80285  4  7.2109   1.80245  1.80285
1355271027  1.80265  5  9.01355  1.80245  1.80285
1355271056  1.80275  6  10.8163  1.80245  1.80285
"""


      EventStore.clear
    }

  }
}
