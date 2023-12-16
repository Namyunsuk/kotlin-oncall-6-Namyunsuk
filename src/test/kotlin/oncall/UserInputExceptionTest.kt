package oncall

import oncall.utils.UserInputException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserInputExceptionTest {
    @Test
    fun `두 번 편성된 경우 예외처리 테스트`() {
        assertThrows<IllegalArgumentException> {
            UserInputException.checkDuplicate(listOf("소", "닭", "알리", "소"))
        }
    }

    @Test
    fun `근무자의 닉네임이 5자를 넘는 경우 예외처리 테스트`() {
        assertThrows<IllegalArgumentException> {
            UserInputException.checkNameLen(listOf("소소소소소소", "닭", "알리", "소"))
        }
    }

    @Test
    fun `근무자의 수가 5~35명을 벗어날 시 예외처리 테스트`() {
        assertThrows<IllegalArgumentException> {
            UserInputException.checkWorkerNum(listOf("소", "닭", "알리", "소"))
        }
    }

    @Test
    fun `입력한 월이 유효하지 않은 경우`() {
        assertThrows<IllegalArgumentException> {
            UserInputException.checkValidMonth(13)
        }
    }

    @Test
    fun `입력한 요일이 유효하지 않은 경우`() {
        assertThrows<IllegalArgumentException> {
            UserInputException.checkValidDay("왈")
        }
    }
}