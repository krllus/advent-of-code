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
        val map = mutableMapOf<Point, MutableList<Int>>()
        for (lineIndex in input.indices) {
            val line = "${input[lineIndex]}.".toCharArray()
            var numberStr = ""
            for (charIndex in line.indices) {
                val char = line[charIndex]
                if (char.isDigit()) numberStr += char
                else {


                    safeExecute {
                        val adj = inputAsGrid[lineIndex - 1][charIndex - 1]
                        if (adj == '*') {
                            val number = numberStr.toInt()
                            val point = Point(lineIndex, charIndex)
                            val elements = map[point] ?: mutableListOf()
                            elements.add(number)
                            map[point] = elements
                        }

                    }
                    safeExecute {
                        val adj = inputAsGrid[lineIndex - 1][charIndex]
                        if (adj == '*') {
                            val number = numberStr.toInt()
                            val point = Point(lineIndex, charIndex)
                            val elements = map[point] ?: mutableListOf()
                            elements.add(number)
                            map[point] = elements
                        }
                    }
                    safeExecute {
                        val adj = inputAsGrid[lineIndex - 1][charIndex + 1]
                        if (adj == '*') {
                            val number = numberStr.toInt()
                            val point = Point(lineIndex, charIndex)
                            val elements = map[point] ?: mutableListOf()
                            elements.add(number)
                            map[point] = elements
                        }
                    }

                    safeExecute {
                        val adj = inputAsGrid[lineIndex][charIndex - 1]
                        if (adj == '*') {
                            val number = numberStr.toInt()
                            val point = Point(lineIndex, charIndex)
                            val elements = map[point] ?: mutableListOf()
                            elements.add(number)
                            map[point] = elements
                        }
                    }
                    safeExecute {
                        val adj = inputAsGrid[lineIndex][charIndex + 1]
                        if (adj == '*') {
                            val number = numberStr.toInt()
                            val point = Point(lineIndex, charIndex)
                            val elements = map[point] ?: mutableListOf()
                            elements.add(number)
                            map[point] = elements
                        }
                    }

                    safeExecute {
                        val adj = inputAsGrid[lineIndex + 1][charIndex - 1]
                        if (adj == '*') {
                            val number = numberStr.toInt()
                            val point = Point(lineIndex, charIndex)
                            val elements = map[point] ?: mutableListOf()
                            elements.add(number)
                            map[point] = elements
                        }
                    }
                    safeExecute {
                        val adj = inputAsGrid[lineIndex + 1][charIndex]
                        if (adj == '*') {
                            val number = numberStr.toInt()
                            val point = Point(lineIndex, charIndex)
                            val elements = map[point] ?: mutableListOf()
                            elements.add(number)
                            map[point] = elements
                        }
                    }
                    safeExecute {
                        val adj = inputAsGrid[lineIndex + 1][charIndex + 1]
                        if (adj == '*') {
                            val number = numberStr.toInt()
                            val point = Point(lineIndex, charIndex)
                            val elements = map[point] ?: mutableListOf()
                            elements.add(number)
                            map[point] = elements
                        }
                    }

                    numberStr = ""
                }
            }
        }

        val result = map.filter { it.value.size >= 2 }.values

        return result.sumOf { list -> list.sumOf { it } }
    }

}

data class Point(val x: Int, val y: Int)

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
        """.trimIndent()(part1 = 4361, part2 = 467835)
    }
}