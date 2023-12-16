package oncall

import oncall.model.EmergencyCalculation

import org.assertj.core.api.Assertions

class EmergencyCalculationTest {
    fun `두번 연속 근무하는 경우 순서를 바꾸는지 테스트`() {
        val emergencyCalculation = EmergencyCalculation()
        val result = emergencyCalculation.duplicateSwitch("조랑말", mutableListOf("조랑말", "얼룩말"))
        Assertions.assertThat(result).isEqualTo(mutableListOf("얼룩말", "조랑말"))
    }
}