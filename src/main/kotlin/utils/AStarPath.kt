package utils

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun main() {

    val rows = 5
    val columns = 8

    val grid = Array(rows) { row ->
        Array(columns) { column ->
            Node(row = row, column = column, value = 'S')
        }
    }

    val origin = grid[0][0]
    val destiny = grid[rows - 1][columns - 1]

    val path = calculatePath(grid, origin, destiny)

    println("path is [$path]")
}

fun calculatePath(grid: Array<Array<Node>>, origin: Node, destiny: Node): Pair<Int, String> {
    val open = ArrayDeque<Node>()
    val closed = mutableSetOf<Node>()

    val cameFrom = mutableMapOf<String, Node>()

    origin.gCost = 0
    origin.fCost = heuristic(origin, destiny)

    open.add(origin)

    while (open.isNotEmpty()) {
        val currentNode = getLowestCostOpenNode(open.toList()) ?: error("there is no node")
        open.remove(currentNode)
        closed.add(currentNode)

        if (currentNode == destiny)
            return reconstructPath(cameFrom, currentNode)

        for (neighbor in currentNode.getNeighbors(grid)) {
            if (closed.contains(neighbor)) {
                continue
            }

            val calcDistance = distance(currentNode, neighbor)

            if (calcDistance == Int.MIN_VALUE)
                continue

            val neighborTempG = currentNode.gCost + calcDistance

            if (neighborTempG < neighbor.gCost) {
                cameFrom[neighbor.name] = currentNode
                neighbor.gCost = neighborTempG
                neighbor.fCost = neighborTempG + heuristic(neighbor, destiny)
                if (!open.contains(neighbor))
                    open.addLast(neighbor)
            }
        }
    }

    error("path not found from $origin to $destiny")
}

fun reconstructPath(cameFrom: MutableMap<String, Node>, currentNode: Node): Pair<Int, String> {
    var totalPath = ""
    var totalPathSize = 0
    var current = currentNode
    while (cameFrom.keys.contains(current.name)) {
        current = cameFrom[current.name] ?: error("not found")
        totalPath = "[${current.value}](${current.name}):${totalPath}"
        totalPathSize++
    }

    return totalPathSize to totalPath
}

fun distance(currentNode: Node, neighbor: Node): Int {
    val valid = currentNode.value == 'S'

    if (valid) return 1

    if (neighbor.value == 'E') {
        return if (currentNode.value == 'z')
            1
        else
            Int.MIN_VALUE
    }

    val diff = neighbor.value - currentNode.value

    if (diff <= 1) {
        return 1
    }

    return Int.MIN_VALUE
}

fun getLowestCostOpenNode(open: List<Node>): Node? {
    return open.minByOrNull { it.fCost }
}

fun heuristic(origin: Node, destination: Node): Int {
    val deltaX = abs(destination.row - origin.row).toDouble()
    val deltaY = abs(destination.column - origin.column).toDouble()
    return sqrt(deltaX.pow(2.0) + deltaY.pow(2.0)).roundToInt()
}

data class Node(
    val row: Int,
    val column: Int,
    val value: Char,
    var fCost: Int = Int.MAX_VALUE,
    var gCost: Int = Int.MAX_VALUE,
    var closed: Boolean = false,
) {

    val name = "$row-$column"

    fun getNeighbors(grid: Array<Array<Node>>): List<Node> {

        val nodes = mutableListOf<Node>()
        // top
        if (row - 1 >= 0) {
            nodes.add(grid[row - 1][column])
        }

        // bottom
        if (row + 1 < grid.size) {
            nodes.add(grid[row + 1][column])
        }

        // left
        if (column - 1 >= 0) {
            nodes.add(grid[row][column - 1])
        }

        // right
        if (column + 1 < grid[0].size) {
            nodes.add(grid[row][column + 1])
        }
        return nodes
    }

    override fun toString(): String {
        return "$name v:${value} f:$fCost g:$gCost"
    }
}