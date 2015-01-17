package c12

import java.util._

import org.junit.Test
import org.junit.Assert._

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-18
 */
class SampleTest {

  @Test def listAdd(): Unit = {
    val list = new ArrayList[String]
    list.add("Milk")
    list add "Suga"

    assertEquals(2, list.size)
  }
}
