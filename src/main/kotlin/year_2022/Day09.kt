package year_2022

import utils.readInput
import kotlin.math.pow

fun main() {

    fun getCandidatesToMove(grid: Array<Array<String>>, headPosition: Position): List<Position> {
        val positions = mutableListOf<Position>()

        val size = grid[0].size

        positions.add(headPosition.copy(i = headPosition.i - 1, j = headPosition.j - 1))
        positions.add(headPosition.copy(i = headPosition.i - 1, j = headPosition.j))
        positions.add(headPosition.copy(i = headPosition.i - 1, j = headPosition.j + 1))

        positions.add(headPosition.copy(i = headPosition.i, j = headPosition.j - 1))
        positions.add(headPosition.copy(i = headPosition.i, j = headPosition.j + 1))

        positions.add(headPosition.copy(i = headPosition.i + 1, j = headPosition.j - 1))
        positions.add(headPosition.copy(i = headPosition.i + 1, j = headPosition.j))
        positions.add(headPosition.copy(i = headPosition.i + 1, j = headPosition.j + 1))

        positions.removeIf { position ->
            position.i < 0 || position.j < 0 || position.i >= size || position.j >= size
        }

        return positions
    }

    fun moveTail(
        headPosition: Position,
        oldHeadPosition: Position,
        tailPosition: Position,
    ): Position {
        val dht = headPosition.distance(tailPosition)
        val doft = oldHeadPosition.distance(tailPosition)

        if (dht == 2.0 && doft == 1.0) {
            return oldHeadPosition
        }

        if (dht <= 1.5) {
            return tailPosition
        }

        if (doft > 1.4) {
            return oldHeadPosition
        }

        return tailPosition
    }

    fun part1(input: List<String>): Int {
        val rowSize = 400
        val columnSize = 400
        val grid = Array(rowSize) {
            Array(columnSize) {
                "."
            }
        }

        // initial position
        var headPosition = Position(rowSize / 2, columnSize / 2)
        var tailPosition = Position(rowSize / 2, columnSize / 2)

        grid[headPosition.j][headPosition.i] = "s"


        for (movement in input.asRopeMovements()) {
            repeat(movement.moveSteps) {
                val oldHeadPosition = headPosition.copy()
                headPosition = when (movement.direction) {
                    Direction.UP -> headPosition.copy(j = headPosition.j - 1)
                    Direction.DOWN -> headPosition.copy(j = headPosition.j + 1)
                    Direction.RIGHT -> headPosition.copy(i = headPosition.i + 1)
                    Direction.LEFT -> headPosition.copy(i = headPosition.i - 1)
                }
                tailPosition = moveTail(headPosition, oldHeadPosition, tailPosition)
                if (tailPosition.i >= rowSize) {
                    error("a tail greater then board size: $tailPosition, head: $headPosition prevHead: $oldHeadPosition input:$movement")
                }

                if (tailPosition.j >= columnSize) {
                    error("b tail greater then board size: $tailPosition")
                }
                grid[tailPosition.j][tailPosition.i] = "#"
            }
        }

        var sum = 0
        for (i in 0 until columnSize) {
            for (j in 0 until rowSize) {
                if (grid[j][i] != ".")
                    sum++
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")

    check(part1(testInput) == 13)
    check(part2(testInput) == 0)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}

fun Collection<String>.asRopeMovements(): Collection<RopeMovement> {
    return this.map { it.asRopeMovement() }
}

fun String.asRopeMovement(): RopeMovement {
    val (first, second) = this.split(" ")
    return RopeMovement(first.asDirection(), second.toInt())
}

data class Position(val i: Int, val j: Int) {
    fun distance(other: Position): Double {
        return kotlin.math.sqrt(
            (other.i - this.i).toDouble().pow(2.0) +
                    (other.j - this.j).toDouble().pow(2.0)
        )
    }
}

data class Rope(
    val head : RopeSegment,
    val tail : RopeSegment
)

data class RopeSegment(
    val position: Position,
    val nextSegment : RopeSegment? = null,
    val prevSegment : RopeSegment? = null
)

data class RopeMovement(val direction: Direction, val moveSteps: Int, var processed: Boolean = false)

fun String.asDirection(): Direction {
    return when (this) {
        "U" -> Direction.UP
        "D" -> Direction.DOWN
        "R" -> Direction.RIGHT
        "L" -> Direction.LEFT
        else -> error("no direction found")
    }
}

enum class Direction {
    UP, DOWN, RIGHT, LEFT;
}