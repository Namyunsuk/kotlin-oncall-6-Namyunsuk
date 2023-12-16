package oncall.utils

import camp.nextstep.edu.missionutils.Console

class UserInputException {
    companion object {

        fun checkMonthAndDay(userInput: String): Pair<Int, String> {
            val (month, day) = userInput.split(",")
            require(checkNumber(month)) { "[ERROR] 월은 숫자로 입력해야 합니다.\n" }
            require(checkValidMonth(month.toInt())) { "[ERROR] 월은 1~12사이 숫자여야 합니다.\n" }
            require(checkValidDay(day)) { "[ERROR] 유효하지 않은 요일입니다.\n" }
            return Pair(month.toInt(), day)
        }

        fun checkWorTurn(userInput: String): List<String> {
            val emergencyTurn = userInput.split(",")
            require(checkNameLen(emergencyTurn)) { "[ERROR] 닉네임은 5자 이하여야 합니다.\n" }
            require(checkDuplicate(emergencyTurn)) { "[ERROR] 두번 편성될 수는 없습니다.\n" }
            require(checkWorkerNum(emergencyTurn)) { "[ERROR] 근무자는 5~35명이어야 합니다.\n" }
            return emergencyTurn
        }

        fun checkDuplicate(userNumbers: List<String>): Boolean {
            val distinctUserNumbers = userNumbers.distinct()
            return userNumbers.size == distinctUserNumbers.size
        }

        fun checkNameLen(workerName: List<String>): Boolean {
            return workerName.all { it.length <= 5 }
        }

        fun checkNumber(userInput: String): Boolean {
            for (numberIndex in userInput.indices) {
                if (userInput[numberIndex].code < '0'.code || userInput[numberIndex].code > '9'.code) {
                    return false
                }
            }
            return true
        }

        fun checkWorkerNum(userInput: List<String>): Boolean {
            return userInput.size in 5..35
        }

        fun checkValidMonth(userInput: Int): Boolean {
            return userInput in 1..12
        }

        fun checkValidDay(userInput: String): Boolean {
            val dayNames = arrayOf("일", "월", "화", "수", "목", "금", "토")
            return userInput in dayNames
        }
    }
}