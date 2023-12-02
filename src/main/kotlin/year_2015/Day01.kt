package year_2015

import Day
import solve

class Day01 : Day(day = 1, year = 2015, "Not Quite Lisp") {
    override fun part1(): Long {
        return inputAsString.toCharArray().sumOf { letter ->
            if (letter == '(') 1L
            else -1L
        }
    }

    override fun part2(): Int {
        var acc = 0
        inputAsString.toCharArray().mapIndexed { index, c ->
            if (c == '(') acc++
            else acc--

            if (acc == -1)
                return index + 1
        }

        return -1
    }
}

fun main() {
    solve<Day01>(offerSubmit = true) {
        """
                    ()())
        """.trimIndent()(part2 = 5)
    }
}