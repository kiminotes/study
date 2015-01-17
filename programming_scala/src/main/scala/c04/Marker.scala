package c04

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
class Marker private( val color: String ) {

  println( "Creating " + this )

  override def toString( ): String = {
    "marker color " + color
  }
}

object Marker {

  private val markers = Map(
    "red" -> new Marker( "red" ),
    "blue" -> new Marker( "blue" ),
    "green" -> new Marker( "green" )
  )
}
