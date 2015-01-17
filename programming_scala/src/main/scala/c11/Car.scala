package c11

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
class Car(val year: Int) {

  private[this] var miles: Int = 0

  def drive(distance: Int) {
    miles += distance
  }

  override def toString: String = "year: " + year + " miles: " + miles
}
