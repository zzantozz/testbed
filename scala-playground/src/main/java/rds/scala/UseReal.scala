package rds.scala

/**
 * Created by IntelliJ IDEA.
 * User: Ryan
 * Date: Jul 18, 2010
 * Time: 4:02:32 PM
 */

object UseReal {
  def main(args: Array[String]) {
    val complex: Complex = new Complex(1.5, 2.3)
    println(complex.re)
    println(complex.im)
  }
}