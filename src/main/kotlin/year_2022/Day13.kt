package year_2022

import utils.readInputAsText

fun main() {

    fun part1(groupInput: String): Int {
        val input = groupInput.split("\n\n")

        var sum = 0

        input.forEachIndexed { index, group ->
            val (line1, line2) = group.split("\n")
            val packet1 = parseLine(line1)
            val packet2 = parseLine(line2)

            if (packet1 isBefore packet2) {
                sum += index
            }

        }

        return sum
    }

    fun part2(groupInput: String): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsText("Day13_test")

    check(part1(testInput) == 13)
    check(part2(testInput) == 0)

    val input = readInputAsText("Day13")
    println(part1(input))
    println(part2(input))
}

fun parseLine(line: String): Packet {
    val root = Packet()



    return root
}

data class Packet(
    val value: Int? = null,
    val parent: Packet? = null,
    val child: List<Packet> = emptyList(),
) {
    infix fun isBefore(packet: Packet): Boolean {
        return false
    }
}