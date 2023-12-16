package oncall

import oncall.utils.Calendar
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class CalendarTest {
    @Test
    fun `월일에 따라 휴일여부 반환 테스트`() {
        val result = Calendar.matchHoliday(5, 5)
        assertThat(result).isTrue()
    }

    @Test
    fun `주말과 법적공휴일 여부 반환 테스트`() {
        val result1 = Calendar.isHoliday(5, 8, "월")
        assertThat(result1).isFalse()
        val result2 = Calendar.isHoliday(5, 6, "토")
        assertThat(result2).isTrue()
    }
}