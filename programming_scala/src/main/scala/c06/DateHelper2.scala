package c06

import java.util.{Calendar, Date}

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
class DateHelper2(number: Int) {

  def days(when: String): Date = {
    val date = Calendar.getInstance()
    when match {
      case "ago" => date.add(Calendar.DAY_OF_MONTH, -number)
      case "from_now" => date.add(Calendar.DAY_OF_MONTH, number)
      case _ => date
    }
    date.getTime
  }
}

object DateHelper2 {

  val ago      = "ago"

  val from_now = "from_now"

  implicit def convertInt2DateHelper2(number: Int): DateHelper2 = new DateHelper2(number)
}
