package year_2023

import Day
import solve

class Day01 : Day(day = 1, year = 2023, "Trebuchet?!") {
    override fun part1(): Int {

        return input.sumOf {
            val a = it.first { c: Char -> c.isDigit() }.digitToInt()
            val b = it.last { c: Char -> c.isDigit() }.digitToInt()
            a * 10 + b
        }

    }

    override fun part2(): Int {
        return input.sumOf { line ->

            val parsedLine = line
                .replace("oneight","oneeight")
                .replace("threeight","threeeight")
                .replace("fiveight","fiveeight")
                .replace("twone","twoone")
                .replace("sevenine","sevennine")
                .replace("eightwo","eighttwo")
                .replace("eighthree","eightthree")
                .replace("nineight","nineeight")

            val result = parsedLine
                .replace("one", "1")
                .replace("two", "2")
                .replace("three", "3")
                .replace("four", "4")
                .replace("five", "5")
                .replace("six", "6")
                .replace("seven", "7")
                .replace("eight", "8")
                .replace("nine", "9")


            val a = result.first { c: Char -> c.isDigit() }.digitToInt()
            val b = result.last { c: Char -> c.isDigit() }.digitToInt()
            a * 10 + b
        }
    }
}

fun main() {
    solve<Day01>(offerSubmit = true) {
        """
           two1nine
           eightwothree
           abcone2threexyz
           xtwone3four
           4nineeightseven2
           zoneight234
           7pqrstsixteen
        """.trimIndent()(part2 = 281)
    }
}