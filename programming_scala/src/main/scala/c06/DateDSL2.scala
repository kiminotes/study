package c06

import DateHelper2._

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
object DateDSL2 {

  def main(args: Array[String]) {
    val past = 2 days ago
    val appointment = 5 days from_now

    println(past)
    println(appointment)
  }
}
