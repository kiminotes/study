package c04

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
class Person(val firstName: String, val lastName: String) {

  var position: String = _

  println("Creating " + toString())

  def this(firstName: String, lastName: String, positionHeld: String) {
    this(firstName, lastName)
    position = positionHeld
  }

  override def toString() : String = {
    firstName + " " + lastName + " holds " + position + " position "
  }

}
