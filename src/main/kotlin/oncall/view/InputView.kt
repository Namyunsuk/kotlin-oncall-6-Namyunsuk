package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.utils.UserInputException

class InputView {
    companion object {
        fun enterMonthAndDay(): Pair<Int, String> {
            print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
            while (true) {
                val userInput = Console.readLine()
                try {
                    return UserInputException.checkMonthAndDay(userInput)
                } catch (e: IllegalArgumentException) {
                    print(e.message)
                }
            }
        }

        fun enterEmergencyTurn(): Pair<List<String>, List<String>> {
            while (true) {
                try {
                    val weekdayTurn = enterWeekdayTurn()
                    val holidayTurn = enterHolidayTurn()
                    return Pair(weekdayTurn, holidayTurn)
                } catch (e: IllegalArgumentException) {
                    print(e.message)
                }
            }
        }

        private fun enterWeekdayTurn(): List<String> {
            print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
            val userInput = Console.readLine()
            return UserInputException.checkWorTurn(userInput)
        }

        private fun enterHolidayTurn(): List<String> {
            print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
            val userInput = Console.readLine()
            return UserInputException.checkWorTurn(userInput)
        }
    }
}