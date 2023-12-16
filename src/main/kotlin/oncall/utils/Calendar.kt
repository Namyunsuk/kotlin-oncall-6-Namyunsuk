package oncall.utils

enum class Calendar(val month: Int, val date: Int) {
    FIRST(1, 1),
    SECOND(3, 1),
    THIRD(5, 5),
    FOURTH(6, 6),
    FIFTH(8, 15),
    SIX(10, 3),
    SEVENTH(10, 9),
    EIGHT(12, 15);

    companion object {
        const val LEGAL_HOLIDAY = "(휴일)"
        const val FIRST_DAY_OF_MONTH = 1

        fun matchHoliday(month: Int, date: Int): Boolean {
            val matchingEnum = entries.find { it.month == month && it.date == date }
            return matchingEnum != null
        }

        fun isHoliday(month: Int, date: Int, day: String): Boolean {
            if (day == "토" || day == "일") return true
            if (matchHoliday(month, date)) return true
            return false
        }

        fun findLastDayOfMonth(month: Int): Int {
            val daysInMonth = when (month) {
                1, 3, 5, 7, 8, 10, 12 -> 31
                4, 6, 9, 11 -> 30
                2 -> 28
                else -> -1
            }
            return daysInMonth
        }

        fun findDayOfWeek(month: Int, daysInMonth: Int, dayOfWeek: String): Map<Int, String> {
            val dayAndDate = mutableMapOf<Int, String>()
            val dayNames = arrayOf("일", "월", "화", "수", "목", "금", "토")
            var currentDayOfWeek = dayNames.indexOf(dayOfWeek)
            for (day in FIRST_DAY_OF_MONTH..daysInMonth) {
                if (matchHoliday(month, day)) {
                    dayAndDate[day] = dayNames[currentDayOfWeek] + LEGAL_HOLIDAY
                    currentDayOfWeek = (currentDayOfWeek + 1) % 7
                    continue
                }
                dayAndDate[day] = dayNames[currentDayOfWeek]
                currentDayOfWeek = (currentDayOfWeek + 1) % 7
            }
            return dayAndDate
        }
    }
}