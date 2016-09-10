package org.example.duedate.scala

import java.time._
import java.time.temporal.ChronoUnit

object DueDateCalculator {

  def calculateDueDate(submitDate: LocalDateTime, turnAround: Duration): LocalDateTime = {
    val endOfDay: LocalDateTime = submitDate withHour 17
    val secondsTilEndOfDay = submitDate.until(endOfDay, ChronoUnit.SECONDS)

    if(turnAround.minusSeconds(secondsTilEndOfDay).isNegative) {
      return submitDate plus turnAround
    }

    calculateDueDate(nextWeekDay9AM(submitDate), turnAround minusSeconds secondsTilEndOfDay)
  }

  def nextWeekDay9AM(submitDate: LocalDateTime): LocalDateTime = {
    val nextDay: LocalDateTime = submitDate plusDays 1

    if (!(Set(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY) contains nextDay.getDayOfWeek)) {
      return nextDay withHour 9
    }

    nextWeekDay9AM(nextDay)
  }

}
