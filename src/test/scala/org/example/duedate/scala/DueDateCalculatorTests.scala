package org.example.duedate.scala

import org.scalatest._
import java.time._

class DueDateCalculatorTests extends FunSuite {

  test("dueDate is greater than submitDate") {
    val submitDate = LocalDateTime.now()
    val dueDate = DueDateCalculator.calculateDueDate(submitDate, Duration.ofHours(1))
    assert(dueDate.isAfter(submitDate))
  }

}
