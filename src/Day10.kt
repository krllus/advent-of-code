fun main() {

    fun part1(input: List<String>): Int {
        val cycleValueMap = mutableMapOf<Int, Int>()

        cycleValueMap[0] = 1

        var cycleCount = 0
        input.forEach {
            if (it.startsWith("noop")) {
                ++cycleCount
                cycleValueMap[cycleCount] =
                    cycleValueMap[cycleCount - 1] ?: error("previous value not defined $cycleCount")
            }

            if (it.startsWith("addx")) {
                val value = it.split(" ").last().toInt()
                ++cycleCount
                cycleValueMap[cycleCount] =
                    cycleValueMap[cycleCount - 1] ?: error("previous value not defined $cycleCount")
                ++cycleCount
                cycleValueMap[cycleCount] =
                    cycleValueMap[cycleCount - 1]?.plus(value) ?: error("previous value not defined $cycleCount")
            }
        }

        var sum = 0

        for (a in listOf(20, 60, 100, 140, 180, 220)) {
            val cal = a * cycleValueMap[a - 1]!!
            sum += cal
        }

        return sum
    }

    fun part2(input: List<String>): String {
        val cycleValueMap = mutableMapOf<Int, Int>()

        var cycleCount = 1
        cycleValueMap[cycleCount] = 1

        val sprite = Array(241) { '0' }
        val rowSize = 40
        input.forEach { str ->

            val parsedIndex = ((cycleCount) % rowSize)
            val sprintIndexRange = setOf(parsedIndex - 1, parsedIndex, parsedIndex + 1).filter { it >= 0 }
            var currentCrtIndex: Int?

            if (str.startsWith("noop")) {
                currentCrtIndex = cycleValueMap[cycleCount]
                if (currentCrtIndex in sprintIndexRange) {
                    sprite[cycleCount - 1] = '#'
                } else {
                    sprite[cycleCount - 1] = '.'
                }

                cycleCount++
                cycleValueMap[cycleCount] =
                    cycleValueMap[cycleCount - 1] ?: error("previous value not defined $cycleCount")

            }

            if (str.startsWith("addx")) {
                val value = str.split(" ").last().toInt()

                currentCrtIndex = cycleValueMap[cycleCount]
                if (currentCrtIndex in sprintIndexRange) {
                    sprite[cycleCount - 1] = '#'
                } else {
                    sprite[cycleCount - 1] = '.'
                }

                cycleCount++
                cycleValueMap[cycleCount] =
                    cycleValueMap[cycleCount - 1] ?: error("previous value not defined $cycleCount")

                currentCrtIndex = cycleValueMap[cycleCount]
                if (currentCrtIndex in sprintIndexRange) {
                    sprite[cycleCount - 1] = '#'
                } else {
                    sprite[cycleCount - 1] = '.'
                }

                cycleCount++
                cycleValueMap[cycleCount] =
                    cycleValueMap[cycleCount - 1]?.plus(value) ?: error("previous value not defined $cycleCount")

            }
        }

        val result = StringBuilder()
        sprite.drop(0).forEachIndexed { index, char ->
            result.append(char)
            if ((index + 1) % rowSize == 0)
                result.append('\n')
        }

        var sum = 0

        for (a in listOf(20, 60, 100, 140, 180, 220)) {
            val cal = a * cycleValueMap[a - 1]!!
            sum += cal
        }
        println(sum)

        return result.toString()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")

//    check(part1(testInput) == 13140)
    println(part2(testInput))

    val input = readInput("Day10")
//    println(part1(input))
    println(part2(input))
}