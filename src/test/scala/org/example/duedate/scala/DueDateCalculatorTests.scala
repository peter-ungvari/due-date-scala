package org.example.duedate.scala

import org.scalatest._
import java.time._

class DueDateCalculatorTests extends FunSuite {

  def testCalculateDueDate(submitDate: LocalDateTime,
                           hours: Int,
                           condition: (LocalDateTime) => Boolean): Unit = {
      val dueDate = DueDateCalculator.calculateDueDate(submitDate, Duration ofHours hours)
      assert(condition(dueDate))
  }

  test("dueDate is greater than submitDate") {
    val submitDate: LocalDateTime = LocalDateTime.now()
    testCalculateDueDate(submitDate, 1, _ isAfter submitDate)
  }

  test("dueDate is from 9AM to 5PM") {
    testCalculateDueDate(
      LocalDateTime.of(2016, 9, 7, 16, 0),
      2,
      10 === _.getHour)
  }

  test("dueDate is from 9AM to 5PM (2)") {
    testCalculateDueDate(
      LocalDateTime.of(2016, 9, 7, 16, 1),
      1,
      LocalDateTime.of(2016, 9, 8, 9, 1) == _)
  }

  test("2016-09-09 2PM + 7 days 4 hours = 2016-09-19 10AM") {
    testCalculateDueDate(
      LocalDateTime.of(2016, 9, 9, 14, 0),
      8 * 5 + 4,
      LocalDateTime.of(2016, 9, 19, 10, 0) == _)
  }

  test("2015-12-31 2PM + 4 hours = 2016-01-01 10AM") {
    testCalculateDueDate(
      LocalDateTime.of(2015, 12, 31, 14, 0),
      4,
      LocalDateTime.of(2016, 1, 1, 10, 0) == _)
  }

}
