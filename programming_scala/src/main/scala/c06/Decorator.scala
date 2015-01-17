package c06

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
object Decorator {

  def main(args: Array[String]) {
    val apartmentApplication = new Check with CreditCheck with CriminalRecordCheck
    println(apartmentApplication.check())

    val employmentApplication = new Check with CriminalRecordCheck with EmploymentCheck
    println(employmentApplication check())
  }
}
