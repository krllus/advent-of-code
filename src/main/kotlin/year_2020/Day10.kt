package year_2020

import Day
import solve

class Day10 : Day(day = 10, year = 2020, "Report Repair") {

    private val targetSum = 2020

    override fun part1(): Int {
        return 0
    }

}

fun main() {
    solve<Day01>(offerSubmit = true) {
        """ 
                       1721
                       979
                       366
                       299
                       675
                       1456
        """.trimIndent()(part1 = 514579)
    }
}