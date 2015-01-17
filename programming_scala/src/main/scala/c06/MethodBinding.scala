package c06

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
object MethodBinding {

  def main(args: Array[String]) {
    val myWriterProfanityFirst = new StringWriterDelegate
      with UpperCaseWriter with ProfanityFilteredWriter
    val myWriterProfanityLast = new StringWriterDelegate
      with ProfanityFilteredWriter with UpperCaseWriter

    myWriterProfanityFirst writeMessage "There is no sin except stupidity"
    myWriterProfanityLast writeMessage "There is no sin except stupidity"

    println(myWriterProfanityFirst)
    println(myWriterProfanityLast)
  }
}
