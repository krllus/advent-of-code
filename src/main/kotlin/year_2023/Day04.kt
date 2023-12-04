package year_2023

import Day
import solve
import kotlin.math.pow

class Day04 : Day(day = 4, year = 2023, "Scratchcards") {

    private fun getCards(): List<Card> {
        val cards = mutableListOf<Card>()
        for (entry in input) {
            val data = entry.split(":")
            val id = data.first().split(" ").last().toInt()
            val numbers = data.last().split("| ")
            val winningNumbers = numbers.first().split(" ").mapNotNull { it.toIntOrNull() }
            val myNumbers = numbers.last().split(" ").mapNotNull { it.toIntOrNull() }
            val card = Card(id = id, winningNumbers = winningNumbers, myNumbers = myNumbers)
            cards.add(card)
        }
        return cards
    }

    override fun part1(): Int {
        return getCards().sumOf { it.calculatePoints() }
    }

    override fun part2(): Int {
        return 0
    }

}

data class Card(
    val id: Int,
    val winningNumbers: List<Int>,
    val myNumbers: List<Int>
) {
    fun calculatePoints(): Int {
        var points = 0
        for(number in myNumbers){
            if(winningNumbers.contains(number)){
                if(points == 0) points = 1
                else points *= 2
            }
        }
        println("id: $id points:$points")
        return points
    }
}

fun main() {
    solve<Day04>(offerSubmit = true) {
        """
Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent()(part1 = 13)
    }
}