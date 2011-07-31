package rds.scala

import java.util.{Locale, Date}
import java.text.DateFormat._

/**
 * Created by IntelliJ IDEA.
 * User: Ryan
 * Date: Jul 18, 2010
 * Time: 3:52:22 PM
 */

object FrenchDate {
  def main(args: Array[String]) {
    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)
    println(df format now)
  }
}