package c11

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
class Buddy {

  def greet(): Unit = {
    println("Hello from Buddy class")
  }

}

object Buddy {

  def greet(): Unit = {
    println("Hello from Buddy object")
  }
}
