package c06

import java.io.StringWriter

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
class StringWriterDelegate extends Writer {

  val writer = new StringWriter()

  override def writeMessage(message: String) = writer.write(message)

  override def toString: String = writer.toString
}
