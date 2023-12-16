package oncall.controller

import oncall.model.EmergencyCalculation
import oncall.utils.Calendar
import oncall.view.InputView
import oncall.view.OutputView

class WorkController {
    private val emergencyWorkSheet = mutableMapOf<String, String>()
    fun start() {
        val emergencyCalculation = EmergencyCalculation()
        val (month, dayOfWeek) = InputView.enterMonthAndDay()
        val (weekdayTurn, holidayTurn) = InputView.enterEmergencyTurn()
        val dateAndDay = emergencyCalculation.makeCalendar(month, dayOfWeek)
        makeEmergencyWorSheet(
            emergencyCalculation, dateAndDay, month, holidayTurn.toMutableList(), weekdayTurn.toMutableList()
        )
        OutputView.printMonthWorkTurn(emergencyWorkSheet)
    }

    private fun makeEmergencyWorSheet(
        emergencyCalculation: EmergencyCalculation, dateInfo: Map<Int, String>, month: Int,
        holidayTurn: MutableList<String>, weekdayTurn: MutableList<String>
    ) {
        var lastWorker = ""
        for ((date, day) in dateInfo) {
            val dayInfo = dayInformation(month, date, day)
            if (Calendar.isHoliday(month, date, day)) {
                lastWorker = emergencyCalculation.addHolidayTurn(emergencyWorkSheet, lastWorker, holidayTurn, dayInfo)
                continue
            }
            lastWorker = emergencyCalculation.addWeekdayTurn(emergencyWorkSheet, lastWorker, weekdayTurn, dayInfo)
        }
    }

    private fun dayInformation(month: Int, date: Int, day: String): String {
        val dayInfo = "${month}월 ${date}일 ${day}"
        return dayInfo
    }
}