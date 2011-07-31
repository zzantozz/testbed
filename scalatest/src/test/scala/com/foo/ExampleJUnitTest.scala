package com.foo

import collection.mutable.ListBuffer
import org.junit.{Before, Test}
import org.junit.Assert._
import org.scalatest.junit.JUnitSuite

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 5/15/11
 * Time: 1:18 AM
 * To change this template use File | Settings | File Templates.
 */

class ExampleJUnitTest extends JUnitSuite {
  var sb: StringBuilder = _
  var lb: ListBuffer[String] = _

  @Before def initialize() {
    sb = new StringBuilder("ScalaTest is ")
    lb = new ListBuffer[String]
  }

  @Test def verifyEasy() {
    // Uses JUnit-style assertions
    sb.append("easy!")
    assertEquals("ScalaTest is easy!", sb.toString)
    assertTrue(lb.isEmpty)
    lb += "sweet"
    try {
      "verbose".charAt(-1)
      fail()
    }
    catch {
      case e: StringIndexOutOfBoundsException => // Expected
    }
  }

  @Test def verifyFun() {
    // Uses ScalaTest assertions
    sb.append("fun!")
    assert(sb.toString === "ScalaTest is fun!")
    assert(lb.isEmpty)
    lb += "sweeter"
    intercept[StringIndexOutOfBoundsException] {
      "concise".charAt(-1)
    }
  }
}