package c04

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
class Car(val year : Int) {
  private var milesDriver : Int = 0

  def mails() = milesDriver

  def drive(distance: Int): Unit = {
    milesDriver += Math.abs(distance)
  }
}
