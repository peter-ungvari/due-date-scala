package org.example.duedate.scala

import org.scalatest._
import java.time._

class DueDateCalculatorTests extends FunSuite {

  test("dueDate is greater than submitDate") {
    val submitDate = LocalDateTime.now()
    val dueDate = DueDateCalculator.calculateDueDate(submitDate, Duration.ofHours(1))
    assert(dueDate.isAfter(submitDate))
  }

  test("dueDate is from 9AM to 5PM") {
    val submitDate = LocalDateTime.of(2016, 9, 7, 16, 0)
    val dueDate = DueDateCalculator.calculateDueDate(submitDate, Duration.ofHours(2))
    assert(10 === dueDate.getHour)
  }

  test("dueDate is from 9AM to 5PM (2)") {
    val submitDate = LocalDateTime.of(2016, 9, 7, 16, 1)
    val dueDate = DueDateCalculator.calculateDueDate(submitDate, Duration.ofHours(1))
    assert(LocalDateTime.of(2016, 9, 8, 9, 1) == dueDate)
  }

  test("2016-09-09 2PM + 7 days 4 hours = 2016-09-19 10AM") {
    val submitDate = LocalDateTime.of(2016, 9, 9, 14, 0)
    val dueDate = DueDateCalculator.calculateDueDate(submitDate, Duration.ofHours(8 * 5 + 4))
    assert(LocalDateTime.of(2016, 9, 19, 10, 0) == dueDate)
  }

  test("2015-12-31 2PM + 4 hours = 2016-01-01 10AM") {
    val submitDate = LocalDateTime.of(2015, 12, 31, 14, 0)
    val dueDate = DueDateCalculator.calculateDueDate(submitDate, Duration.ofHours(4))
    assert(LocalDateTime.of(2016, 1, 1, 10, 0) == dueDate)
  }

}
