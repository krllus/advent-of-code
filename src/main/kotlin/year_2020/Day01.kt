package year_2020

import Day
import solve

class Day01 : Day(day = 1, year = 2020, "Report Repair") {

    private val targetSum = 2020

    override fun part1(): Int {
        val set = mutableSetOf<Int>()
        for (number in inputAsInts) {
            val difference = targetSum - number
            if(set.contains(difference)){
                return difference * number
            }
            set.add(number)
        }
        return 0
    }

    override fun part2(): Int {
        val set = HashMap<Int, Element>()

        for (i in inputAsInts.indices){
            for(j in i+1..<inputAsInts.size){
                val v1 = inputAsInts[i]
                val v2 = inputAsInts[j]
                val sum = v1 + v2
                val element = Element(v1, v2)
                set[sum] = element
            }
        }

        for (number in inputAsInts) {
            val difference = targetSum - number
            val element = set[difference]
            if(element != null){
                return number * element.v1 * element.v2
            }
        }

        return 0
    }

}

data class Element(val v1 : Int, val v2 : Int)

fun main() {
    solve<Day01>(offerSubmit = true) {
        """ 
                       1721
                       979
                       366
                       299
                       675
                       1456
        """.trimIndent()(part1 = 514579, part2 = 241861950)
    }
}