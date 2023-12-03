package year_2023

import Day
import solve

class Day03 : Day(day = 3, year = 2023, "") {
    override fun part1(): Int {
        var sum = 0
        for (i in inputAsGrid.indices) {
            var numberStr = ""
            var numberStrAdjacent = ""
            for (j in 0..<inputAsGrid[i].size) {
                val char = inputAsGrid[i][j]
                if (char.isDigit()) {
                    numberStr += char
                    safeExecute { numberStrAdjacent += inputAsGrid[i - 1][j - 1] }
                    safeExecute { numberStrAdjacent += inputAsGrid[i - 1][j] }
                    safeExecute { numberStrAdjacent += inputAsGrid[i - 1][j + 1] }

                    safeExecute { numberStrAdjacent += inputAsGrid[i][j - 1] }
                    safeExecute { numberStrAdjacent += inputAsGrid[i][j + 1] }

                    safeExecute { numberStrAdjacent += inputAsGrid[i + 1][j - 1] }
                    safeExecute { numberStrAdjacent += inputAsGrid[i + 1][j] }
                    safeExecute { numberStrAdjacent += inputAsGrid[i + 1][j + 1] }
                } else {
                    if(numberStrAdjacent.any { c -> !(c.isDigit() || c == '.') }){
                        val number = numberStr.toInt()
                        sum += number
                    }
                    numberStr = ""
                    numberStrAdjacent = ""
                }
            }
        }

        return sum
    }

}

fun safeExecute(block: () -> Unit) {
    try {
        block.invoke()
    } catch (_: Exception) {

    }
}

fun main() {
    solve<Day03>(offerSubmit = true) {
        """
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
        """.trimIndent()(part1 = 4361)
    }
}