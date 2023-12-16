package oncall.model

import oncall.utils.Calendar

class EmergencyCalculation {

    fun addWeekdayTurn(
        emergencyWorkSheet: MutableMap<String, String>,
        lastInputWorker: String,
        weekdayTurn: MutableList<String>,
        dayInfo: String
    ): String {
        var lastInputWorker1 = lastInputWorker
        duplicateSwitch(lastInputWorker1, weekdayTurn)
        emergencyWorkSheet[dayInfo] = weekdayTurn[0]
        lastInputWorker1 = weekdayTurn[0]
        shiftListValues(weekdayTurn)
        return lastInputWorker1
    }

    fun addHolidayTurn(
        emergencyWorkSheet: MutableMap<String, String>,
        lastInputWorker: String,
        holidayTurn: MutableList<String>,
        dayInfo: String
    ): String {
        var lastInputWorker1 = lastInputWorker
        duplicateSwitch(lastInputWorker1, holidayTurn)
        emergencyWorkSheet[dayInfo] = holidayTurn[0]
        lastInputWorker1 = holidayTurn[0]
        shiftListValues(holidayTurn)
        return lastInputWorker1
    }

    fun duplicateSwitch(lastInputWorker1: String, weekdayTurn: MutableList<String>) {
        if (lastInputWorker1 == weekdayTurn[0]) {
            switchTurn(weekdayTurn)
        }
    }

    fun makeCalendar(month: Int, dayOfWeek: String): Map<Int, String> {
        val daysInMonth = Calendar.findLastDayOfMonth(month)
        return Calendar.findDayOfWeek(month, daysInMonth, dayOfWeek)
    }


    fun switchTurn(turnInfo: MutableList<String>) {
        val switchWorker = turnInfo[0]
        turnInfo[0] = turnInfo[1]
        turnInfo[1] = switchWorker
    }

    fun shiftListValues(list: MutableList<String>) {
        val lastIndex = list.size - 1
        for (i in 0 until lastIndex) {
            list[i] = list[i + 1]
        }
        list[lastIndex] = list[0]
    }
}