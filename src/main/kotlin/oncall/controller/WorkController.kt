package oncall.controller

import oncall.utils.Calendar
import oncall.view.InputView
import oncall.view.OutputView

class WorkController {
    val emergencyWorkSheet = mutableMapOf<String, String>()
    fun start() {
        val (month, dayOfWeek) = InputView.enterMonthAndDay()
        val weekdayTurn = InputView.enterWeekdayTurn().toMutableList()
        val holidayTurn = InputView.enterHolidayTurn().toMutableList()
        val dateAndDay = makeCalendar(month, dayOfWeek)
        makeEmergencyWorSheet(dateAndDay, month, holidayTurn, weekdayTurn)
        OutputView.printMonthWorkTurn(emergencyWorkSheet)
    }

    private fun makeEmergencyWorSheet(
        dateAndDay: Map<Int, String>, month: Int, holidayTurn: MutableList<String>, weekdayTurn: MutableList<String>
    ) {
        var lastInputWorker = ""
        for ((date, day) in dateAndDay) {
            val dayInfo = dayInformation(month, date, day)
            if (Calendar.isHoliday(month, date, day)) {
                lastInputWorker = addHolidayTurn(lastInputWorker, holidayTurn, dayInfo)
                continue
            }
            lastInputWorker = addWeekdayTurn(lastInputWorker, weekdayTurn, dayInfo)
        }
    }

    private fun addWeekdayTurn(lastInputWorker: String, weekdayTurn: MutableList<String>, dayInfo: String): String {
        var lastInputWorker1 = lastInputWorker
        duplicateSwitch(lastInputWorker1, weekdayTurn)
        emergencyWorkSheet[dayInfo] = weekdayTurn[0]
        lastInputWorker1 = weekdayTurn[0]
        shiftListValues(weekdayTurn)
        return lastInputWorker1
    }

    private fun addHolidayTurn(lastInputWorker: String, holidayTurn: MutableList<String>, dayInfo: String): String {
        var lastInputWorker1 = lastInputWorker
        duplicateSwitch(lastInputWorker1, holidayTurn)
        emergencyWorkSheet[dayInfo] = holidayTurn[0]
        lastInputWorker1 = holidayTurn[0]
        shiftListValues(holidayTurn)
        return lastInputWorker1
    }

    private fun duplicateSwitch(lastInputWorker1: String, weekdayTurn: MutableList<String>) {
        if (lastInputWorker1 == weekdayTurn[0]) {
            switchTurn(weekdayTurn)
        }
    }

    private fun dayInformation(month: Int, date: Int, day: String): String {
        val dayInfo = "${month}월 ${date}일 ${day}"
        return dayInfo
    }

    private fun makeCalendar(month: Int, dayOfWeek: String): Map<Int, String> {
        val daysInMonth = Calendar.findLastDayOfMonth(month)
        return Calendar.findDayOfWeek(month, daysInMonth, dayOfWeek)
    }


    private fun switchTurn(turnInfo: MutableList<String>) {
        val switchWorker = turnInfo[0]
        turnInfo[0] = turnInfo[1]
        turnInfo[1] = switchWorker
    }

    private fun shiftListValues(list: MutableList<String>) {
        val lastIndex = list.size - 1
        for (i in 0 until lastIndex) {
            list[i] = list[i + 1]
        }
        list[lastIndex] = list[0]
    }
}