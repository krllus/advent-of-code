fun main() {

    fun processLine(line: String): Int {
        for (index in 4..line.length - 4) {
            val a = line[index - 4]
            val b = line[index - 3]
            val c = line[index - 2]
            val d = line[index - 1]
            if (a != b && a != c && a != d && b != c && b != d && c != d) {
                return index
            }
        }
        error("index not found")
    }

    fun processLine(line: String, messageLength: Int): Int {
        for (index in messageLength until line.length) {
            val windowStart = 0 + index - messageLength
            val windowEnd = index
            val window = line.subSequence(windowStart, windowEnd)
            var repeatChar = false
            for (windowIndex in window.indices) {
                val char = window[windowIndex]
                for (searchIndex in windowIndex + 1 until window.length) {
                    val searchChar = window[searchIndex]
                    if (char == searchChar) {
                        repeatChar = true
                        continue
                    }
                }
            }
            if (!repeatChar)
                return index
        }

        error("index not found")
    }

    fun part1(input: List<String>): Int {
        return input.map { line ->
            processLine(line, 4)
        }.sumOf { it }
    }

    fun part2(input: List<String>): Int {
        return input.map { line ->
            processLine(line, 14)
        }.sumOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
//    check(part1(testInput) == 39)
//    check(part2(testInput) == 0)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}