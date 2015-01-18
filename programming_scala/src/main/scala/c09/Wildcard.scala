package c09

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-18
 */
object DayOfWeek extends Enumeration {
  val SUNDAY = Value("Sunday")
  val MONDAY = Value("Monday")
  val TUESDAY = Value("Tuesday")
  val WEDNESDAY = Value("Wednesday")
  val THURSDAY = Value("Thursday")
  val FRIDAY = Value("Friday")
  val SATURDAY = Value("Saturday")
}
object Wildcard {

  def activity(day: DayOfWeek.Value): Unit = {
    day match {
      case DayOfWeek.SUNDAY => println("Eat, sleep, repeat... ")
      case DayOfWeek.SATURDAY => println("Hangout with friends... ")
      case _ => println("...code for fun...")
    }
  }

  def main(args: Array[String]) {
    activity(DayOfWeek.SATURDAY)
    activity(DayOfWeek.MONDAY)
  }
}
