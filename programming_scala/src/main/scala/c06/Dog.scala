package c06

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
class Dog(val name: String) extends Animal with Friend {

  override def listen(): Unit = println(name + "'s listening quietly")
}
