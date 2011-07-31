package rds.scala

/**
 * Created by IntelliJ IDEA.
 * User: Ryan
 * Date: Jul 18, 2010
 * Time: 3:57:44 PM
 */

object Timer {
  def oncePerSecond(callback: () => Unit) {
    while (true) {callback(); Thread sleep 1000}
  }

  def timeFlies() {
    println("time files like an arrow")
  }

  def main(args: Array[String]) {
//    oncePerSecond(timeFlies)
    oncePerSecond(() => println("time flies like an arrow..."))
  }
}