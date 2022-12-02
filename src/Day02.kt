import java.lang.IllegalArgumentException

fun main() {



    fun part1(input: List<String>): Int {
        var sum = 0

        for (round in input) {
            val plays = round.split(" ")
            val one = plays[0]
            val two = plays[1]

            val rockPaperScissorPart1 = RockPaperScissor.fromPartOne(one)?: throw IllegalArgumentException()
            val rockPaperScissorPart2 = RockPaperScissor.fromPartTwo(two)?: throw IllegalArgumentException()

            val score = calculateScore(rockPaperScissorPart1, rockPaperScissorPart2)
            sum += score
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        for (round in input) {
            val plays = round.split(" ")
            val one = plays[0]
            val two = plays[1]

            val opponent = RockPaperScissor.fromPartOne(one)?: throw IllegalArgumentException()
            val rockPaperScissorPart2 = compareResult(opponent, two) ?: throw IllegalArgumentException()

            val score = calculateScore(opponent, rockPaperScissorPart2)
            sum += score
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

// rock     - A
// paper    - B
// scissor  - C

// rock     - x
// paper    - y
// scisor   - z

fun calculateScore(one: RockPaperScissor, two: RockPaperScissor): Int {

    val choose = when (two) {
        RockPaperScissor.ROCK -> 1
        RockPaperScissor.PAPER -> 2
        RockPaperScissor.SCISSOR -> 3
    }

    val result = compareInputs(one, two)

    return choose + result
}

fun compareInputs(opponent : RockPaperScissor, mine : RockPaperScissor) : Int {

    var matchResult = ""

    // draw
    if(opponent == mine)
        matchResult = "draw"

    // rock
    if(opponent == RockPaperScissor.ROCK){
        if(mine == RockPaperScissor.PAPER){
            matchResult = "win"
        }
        if(mine == RockPaperScissor.SCISSOR){
            matchResult = "lost"
        }
    }

    // paper
    if(opponent == RockPaperScissor.PAPER){
        if(mine == RockPaperScissor.SCISSOR){
            matchResult = "win"
        }
        if(mine == RockPaperScissor.ROCK){
            matchResult = "lost"
        }

    }

    // paper
    if(opponent == RockPaperScissor.SCISSOR){
        if(mine == RockPaperScissor.ROCK){
            matchResult = "win"
        }
        if(mine == RockPaperScissor.PAPER){
            matchResult = "lost"
        }
    }


    return when (matchResult) {
        "lost" -> 0
        "draw" -> 3
        "win" -> 6
        else -> 0
    }
}

fun compareResult(opponent : RockPaperScissor, input : String) : RockPaperScissor? {

    // lose     - x
    // draw     - y
    // win      - z

    val matchResult = when (input.lowercase()) {
        "x" -> "lose"
        "y" -> "draw"
        "z" -> "win"
        else -> throw IllegalArgumentException()
    }


    // draw
    if(matchResult == "draw")
        return opponent

    // rock
    if(opponent == RockPaperScissor.ROCK){
        if(matchResult == "win"){
            return RockPaperScissor.PAPER
        }
        if(matchResult == "lose"){
            return RockPaperScissor.SCISSOR
        }
    }

    // paper
    if(opponent == RockPaperScissor.PAPER){
        if(matchResult == "win"){
            return RockPaperScissor.SCISSOR
        }
        if(matchResult == "lose"){
            return RockPaperScissor.ROCK
        }

    }

    // paper
    if(opponent == RockPaperScissor.SCISSOR){
        if(matchResult == "win"){
            return RockPaperScissor.ROCK
        }
        if(matchResult == "lose"){
            return RockPaperScissor.PAPER
        }
    }

    return null
}

enum class RockPaperScissor {
    ROCK,
    PAPER,
    SCISSOR;

    companion object {
        fun fromPartOne(input: String): RockPaperScissor? {
            return when (input.lowercase()) {
                "a" -> ROCK
                "b" -> PAPER
                "c" -> SCISSOR
                else -> null
            }
        }

        fun fromPartTwo(input: String): RockPaperScissor? {
            return when (input.lowercase()) {
                "x" -> ROCK
                "y" -> PAPER
                "z" -> SCISSOR
                else -> null
            }
        }
    }
}
