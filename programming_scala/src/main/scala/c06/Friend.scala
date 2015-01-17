package c06

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
trait Friend {

  val name: String

  def listen() = println("Your friend " + name + " is listening")

}
