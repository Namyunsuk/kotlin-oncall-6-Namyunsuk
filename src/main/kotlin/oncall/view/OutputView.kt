package oncall.view

class OutputView {
    companion object {
        fun printMonthWorkTurn(workTurnSheet: Map<String, String>) {
            for ((days, workerName) in workTurnSheet) {
                println("${days} ${workerName}")
            }
        }
    }
}