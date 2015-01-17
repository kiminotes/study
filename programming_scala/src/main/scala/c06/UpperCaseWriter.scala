package c06

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
trait UpperCaseWriter extends Writer {

  abstract override def writeMessage(message: String) =
    super.writeMessage(message.toUpperCase)

}
