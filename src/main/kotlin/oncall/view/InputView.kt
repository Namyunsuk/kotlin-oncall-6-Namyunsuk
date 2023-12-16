package oncall.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    companion object {
        fun enterMonthAndDay(): Pair<Int, String> {
            print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
            val userInput = Console.readLine()
            val (month, day) = userInput.split(",")
            return Pair(month.toInt(), day)
        }

        fun enterWeekdayTurn():List<String>{
            print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
            val userInput = Console.readLine()
            val weekdayTurn = userInput.split(",")
            return weekdayTurn
        }

        fun enterHolidayTurn():List<String>{
            print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
            val userInput = Console.readLine()
            val holidayTurn = userInput.split(",")
            return holidayTurn
        }
    }
}