package oncall

import oncall.controller.WorkController

fun main() {
    val workController = WorkController()
    workController.start()
}
