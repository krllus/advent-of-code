package year_2022

import utils.readInput

fun main() {

    fun printGrid(grid: Array<Array<String>>) {
        val row = grid.size
        val col = grid[0].size

        for (i in 0..row) {
            for (j in 0..col)
                print(grid[i][j])
            println()
        }
    }

    fun part1(input: List<String>): Int {

        val rows = 500
        val columns = 1000

        val grid = Array(rows) { _ ->
            Array(columns) { _ ->
                "."
            }
        }

        val moves = arrayListOf<Pair<Int, Int>>()

        for (line in input) {
            val entries = line.split(" -> ")

            // extract moves
            for (entry in entries) {
                val latlon = entry.split(",")
                val lat = latlon.first().toIntOrNull() ?: error("lat not able to be parsed")
                val lon = latlon.last().toIntOrNull() ?: error("lon not able to be parsed")
                moves.add(Pair(lat, lon))
            }

            // process moves


            // clear moves list
        }

        printGrid(grid)

        return 24
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")

    check(part1(testInput) == 24)
    check(part2(testInput) == 0)

    val input = readInput("Day14")
    println(part1(input))
    println(part2(input))
}