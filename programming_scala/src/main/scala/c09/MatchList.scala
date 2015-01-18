package c09

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-18
 */
object MatchList {

  def activity(day: String): Unit = {
    day match {
      case "Sunday" => print("Eat, sleep, repeat... ")
      case "Saturday" => print("Hangout with friends... ")
      case "Monday" => print("... code for fun... ")
      case "Friday" => print("...read a good book... ")
    }
  }

  def main(args: Array[String]) {
    List("Monday", "Sunday", "Saturday").foreach { activity }
  }

}
