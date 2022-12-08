import kotlin.math.max

fun main() {

    fun isVisibleFromSides(grid: Array<Array<Int>>, i: Int, j: Int, verbose: Boolean = false): Boolean {
        val currentTreeHeight = grid[i][j]

        val rowSize = grid[0].size
        val columnSize = grid.size

        val topToBottomElements = mutableListOf<Int>()
        val rightToLeftElements = mutableListOf<Int>()
        val leftToRightElements = mutableListOf<Int>()
        val bottomToTopElements = mutableListOf<Int>()

        for (e in 0 until i) {
            topToBottomElements.add(grid[e][j])
        }

        for (e in 0 until j) {
            rightToLeftElements.add(grid[i][e])
        }

        for (e in i + 1 until rowSize) {
            bottomToTopElements.add(grid[e][j])
        }

        for (e in j + 1 until columnSize) {
            leftToRightElements.add(grid[i][e])
        }

        val visibleTopToBottom = topToBottomElements.all { it < currentTreeHeight }
        val visibleRightToLeft = rightToLeftElements.all { it < currentTreeHeight }
        val visibleLeftToRight = leftToRightElements.all { it < currentTreeHeight }
        val visibleBottomToTop = bottomToTopElements.all { it < currentTreeHeight }

        val result = visibleTopToBottom || visibleRightToLeft || visibleLeftToRight || visibleBottomToTop

        if (verbose)
            println("item[$i][$j](${grid[i][j]}) = $result")

        return result
    }

    fun part1(input: List<String>): Int {
        var visibleTree = 0
        val rows = input.size
        val columns = input[0].length

        val grid = Array(rows) { i ->
            Array(columns) { j ->
                input[i][j].digitToInt()
            }
        }

        for (i in 1 until rows - 1) {
            for (j in 1 until columns - 1) {
                if (isVisibleFromSides(grid, i, j))
                    visibleTree += 1
            }
        }

        val perimeter = rows + rows + columns + columns - 4

        return visibleTree + perimeter
    }

    fun calculateScenicValue(grid: Array<Array<Int>>, i: Int, j: Int, verbose: Boolean = false): Int {
        val currentTreeHeight = grid[i][j]

        val rowSize = grid[0].size
        val columnSize = grid.size

        val topToBottomElements = mutableListOf<Int>()
        val rightToLeftElements = mutableListOf<Int>()
        val leftToRightElements = mutableListOf<Int>()
        val bottomToTopElements = mutableListOf<Int>()

        for (e in 0 until i) {
            topToBottomElements.add(grid[e][j])
        }

        for (e in 0 until j) {
            rightToLeftElements.add(grid[i][e])
        }

        for (e in i + 1 until rowSize) {
            bottomToTopElements.add(grid[e][j])
        }

        for (e in j + 1 until columnSize) {
            leftToRightElements.add(grid[i][e])
        }

        topToBottomElements.reverse()
        rightToLeftElements.reverse()

        var visibleTopToBottom = topToBottomElements.indexOfFirst { it >= currentTreeHeight }
        var visibleRightToLeft = rightToLeftElements.indexOfFirst { it >= currentTreeHeight }
        var visibleLeftToRight = leftToRightElements.indexOfFirst { it >= currentTreeHeight }
        var visibleBottomToTop = bottomToTopElements.indexOfFirst { it >= currentTreeHeight }

        if(visibleTopToBottom == -1) visibleTopToBottom = topToBottomElements.size - 1
        if(visibleRightToLeft == -1) visibleRightToLeft = rightToLeftElements.size - 1
        if(visibleLeftToRight == -1) visibleLeftToRight = leftToRightElements.size - 1
        if(visibleBottomToTop == -1) visibleBottomToTop = bottomToTopElements.size - 1

        visibleTopToBottom++
        visibleRightToLeft++
        visibleLeftToRight++
        visibleBottomToTop++

        val result = visibleTopToBottom * visibleRightToLeft * visibleLeftToRight * visibleBottomToTop

        if (verbose) {
            println("item[$i][$j](${grid[i][j]}) = $result")
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val rows = input.size
        val columns = input[0].length

        val grid = Array(rows) { i ->
            Array(columns) { j ->
                input[i][j].digitToInt()
            }
        }

        var maxScenicValue = 0

        for (i in 1 until rows - 1) {
            for (j in 1 until columns - 1) {
                val computedScenicValue = calculateScenicValue(grid, i, j, false)
                maxScenicValue = max(maxScenicValue, computedScenicValue)
            }
        }

        return maxScenicValue
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")

    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input)) //1800, 2573 not right
    println(part2(input))
}