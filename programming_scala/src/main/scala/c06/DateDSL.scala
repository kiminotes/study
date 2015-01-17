package c06

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
object DateDSL {

  implicit def convertInt2DateHelper(number: Int): DateHelper = new DateHelper(number)

  def main(args: Array[String]) {
    val ago = "ago"
    val from_now = "from_now"

    val past = 2 days ago
    val appointment = 5 days from_now

    println(past)
    println(appointment)
  }
}
