package year_2023

import Day
import solve

class Day02 : Day(day = 2, year = 2023, "Cube Conundrum") {

    private fun getGames() : Set<Game> {
        val games = mutableSetOf<Game>()

        for(entry in input){
            val gameStr = entry.split(":")
            val id = gameStr.first().split(" ").last().toInt()
            val rounds = gameStr.last().split(";")

            for(round in rounds){
                val cubes = round.split(",")

                var red = 0
                var green = 0
                var blue = 0

                for(cube in cubes){
                    if (cube.contains("red")) red = cube.replace("[^0-9]".toRegex(),"").toInt()
                    if (cube.contains("green")) green = cube.replace("[^0-9]".toRegex(),"").toInt()
                    if (cube.contains("blue")) blue = cube.replace("[^0-9]".toRegex(),"").toInt()
                }

                games.add(Game(id, red, green, blue))
            }
        }
        return games
    }



    override fun part1(): Int{
        return getGames().groupBy { it.id }
            .mapNotNull {
                if(it.value.any {game -> !game.isPossible() }) null
                else it.key
            }.sum()
    }

    override fun part2(): Int{
        return getGames().groupBy { it.id }
            .map {
                val maxRed = it.value.maxBy { game -> game.red }.red
                val maxGreen = it.value.maxBy { game -> game.green }.green
                val maxBlue = it.value.maxBy { game -> game.blue }.blue

                maxRed * maxGreen * maxBlue
            }.sum()
    }
}

data class Game(
    val id : Int,
    val red : Int,
    val green : Int,
    val blue : Int
){
    fun isPossible() : Boolean = red <= 12 && green <= 13 && blue <= 14
}

fun main() {
    solve<Day02>(offerSubmit = true) {
        """ 
                      Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                      Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                      Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                      Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                      Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()(part1 = 8, 2286)
    }
}