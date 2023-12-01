package year_2022

import utils.readInput

fun main() {
    fun part1(input: List<String>): Int {
        val caloriesCountByElf = arrayListOf<Int>()
        var currentElfCaloriesCount = 0

        for(index in input.indices){
            val element = input[index]
            if(element.isEmpty()){
                caloriesCountByElf.add(currentElfCaloriesCount)
                currentElfCaloriesCount = 0
                continue
            }
            currentElfCaloriesCount += element.toInt()

            if(index == input.size -1){
                caloriesCountByElf.add(currentElfCaloriesCount)
            }
        }

        return caloriesCountByElf.maxBy { it }
    }

    fun part2(input: List<String>): Int {
        val caloriesCountByElf = arrayListOf<Int>()
        var currentElfCaloriesCount = 0

        for(index in input.indices){
            val element = input[index]
            if(element.isEmpty()){
                caloriesCountByElf.add(currentElfCaloriesCount)
                currentElfCaloriesCount = 0
                continue
            }
            currentElfCaloriesCount += element.toInt()

            if(index == input.size -1){
                caloriesCountByElf.add(currentElfCaloriesCount)
            }
        }

        caloriesCountByElf.sortBy { it }

        return caloriesCountByElf.takeLast(3).sumOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
