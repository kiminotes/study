package c04

import ChecksumAccumulator.calculate

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-24
 */
object Summer {

  def main(args: Array[String]) {
    for (arg <- args) {
      println(arg + ": " + calculate(arg))
    }
  }

}
