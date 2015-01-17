package c05

import java.util
import java.util._

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
object Generics {

  def main(args: Array[String]) {
    var list1 : List[Int] = new util.ArrayList[Int]()
    var list2 = new util.ArrayList[Int]()

    list2 add 1
    list2 add 2

    var total = 0
    for (val index <- 0 until list2.size()) {
      total += list2 get index
    }

    println("Total is " + total)
  }

}
