package c06

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
trait ProfanityFilteredWriter extends Writer {

  abstract override def writeMessage(message: String) =
    super.writeMessage(message.replace("stupid", "s-----"))
}
