package c06

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
trait CriminalRecordCheck extends Check {

  override def check(): String = "Check Criminal Rcords..." + super.check()
}
