package year_2023

import Day
import solve
import utils.isStar

class Day03 : Day(day = 3, year = 2023, "Gear Ratios") {
    override fun part1(): Int {
        val matrix = input.map { "$it.".toList() }
        var sum = 0
        for (i in matrix.indices) {
            var numberStr = ""
            var numberStrAdjacent = ""
            for (j in 0..<matrix[i].size) {
                val char = matrix[i][j]
                if (char.isDigit()) {
                    numberStr += char
                    safeExecute { numberStrAdjacent += matrix[i - 1][j - 1] }
                    safeExecute { numberStrAdjacent += matrix[i - 1][j] }
                    safeExecute { numberStrAdjacent += matrix[i - 1][j + 1] }

                    safeExecute { numberStrAdjacent += matrix[i][j - 1] }
                    safeExecute { numberStrAdjacent += matrix[i][j + 1] }

                    safeExecute { numberStrAdjacent += matrix[i + 1][j - 1] }
                    safeExecute { numberStrAdjacent += matrix[i + 1][j] }
                    safeExecute { numberStrAdjacent += matrix[i + 1][j + 1] }
                } else {
                    if (numberStrAdjacent.any { c -> !(c.isDigit() || c == '.') }) {
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

    override fun part2(): Int {
        val map = mutableMapOf<Point, ArrayList<Int>>()
        val matrix = input.map { "$it.".toList() }
        for (i in matrix.indices) {
            var numberStr = ""
            for (j in 0..<matrix[i].size) {
                val char = matrix[i][j]
                if (char.isDigit()) numberStr += char
                else {
                    safeExecute {
                        if (matrix[i - 1][j - 1].isStar()) {
                            map.addElement(Point(i - 1, j - 1), numberStr.toInt())
                        }
                    }
                    safeExecute {
                        if (matrix[i - 1][j].isStar()) {
                            map.addElement(Point(i - 1, j), numberStr.toInt())
                        }
                    }
                    safeExecute {
                        if (matrix[i - 1][j + 1].isStar()) {
                            map.addElement(Point(i - 1, j + 1), numberStr.toInt())
                        }
                    }

                    safeExecute {
                        if (matrix[i][j - 1].isStar()) {
                            map.addElement(Point(i, j - 1), numberStr.toInt())
                        }
                    }
                    safeExecute {
                        if (matrix[i][j].isStar()) {
                            map.addElement(Point(i, j), numberStr.toInt())
                        }
                    }
                    safeExecute {
                        if (matrix[i][j + 1].isStar()) {
                            map.addElement(Point(i, j + 1), numberStr.toInt())
                        }
                    }

                    safeExecute {
                        if (matrix[i + 1][j - 1].isStar()) {
                            map.addElement(Point(i + 1, j - 1), numberStr.toInt())
                        }
                    }
                    safeExecute {
                        if (matrix[i + 1][j].isStar()) {
                            map.addElement(Point(i + 1, j), numberStr.toInt())
                        }
                    }
                    safeExecute {
                        if (matrix[i + 1][j + 1].isStar()) {
                            map.addElement(Point(i + 1, j + 1), numberStr.toInt())
                        }
                    }

                    numberStr = ""
                }
            }
        }

        val result = map.filter { it.value.size == 2 }.values

        return result.sumOf { list -> list[0] * list[1] }
    }

}

data class Point(val x: Int, val y: Int)

fun safeExecute(block: () -> Unit) {
    try {
        block.invoke()
    } catch (_: Exception) {

    }
}

fun MutableMap<Point, ArrayList<Int>>.addElement(point: Point, element: Int) {
    val list = this[point] ?: arrayListOf()
    list.add(element)
    this[point] = list
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
        """.trimIndent()(part1 = 4361, part2 = 467835)
    }
}