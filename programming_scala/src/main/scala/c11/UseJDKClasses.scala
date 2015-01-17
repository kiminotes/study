package c11

import java.lang.reflect.Method
import java.util.Date

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
object UseJDKClasses {

  def main(args: Array[String]) {
    println("Today is " + new Date)

    val methods = getClass.getMethods
    methods.foreach { method: Method => println(method.getName)}
  }
}
