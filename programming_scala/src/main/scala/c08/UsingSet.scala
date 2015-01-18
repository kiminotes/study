package c08

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-18
 */
object UsingSet {

  def main(args: Array[String]) {
    val colors1 = Set("Blue", "Green", "Red")
    var colors = colors1
    println("colors1 (colors): " + colors)

    colors += "Black"
    println("colors: " + colors)
    println("colors1: " + colors1)
  }
}
