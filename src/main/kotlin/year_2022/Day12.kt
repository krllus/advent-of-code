package year_2022

import utils.Node
import utils.calculatePath
import utils.readInput

fun main() {

    fun part1(input: List<String>): Int {
        val rows = input.size
        val columns = input[0].length
        var origin: Node? = null
        var destiny: Node? = null
        val grid = Array(rows) { row ->
            Array(columns) { column ->
                val char = input[row][column]
                val createdNode = Node(row = row, column = column, value = char)
                if (char == 'S')
                    origin = createdNode
                if (char == 'E')
                    destiny = createdNode
                createdNode
            }
        }

        val (pathSize, path) = calculatePath(grid, origin!!, destiny!!)
        println("size: $pathSize, path: $path")
        return pathSize
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")

    check(part1(testInput) == 31)
    check(part2(testInput) == 0)

    val input = readInput("Day12")
    println(part1(input)) // w: 464, r: 476
    println(part2(input)) // 

}