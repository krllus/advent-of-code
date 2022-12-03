import java.lang.IllegalArgumentException

fun main() {

    fun proccessInput(input : String) : Pair<String, String>{
        val middle = input.length / 2
        val first = input.substring(0, middle)
        val second = input.substring(middle, input.length)
        return (first to second)
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {entry ->
            val (firstHalf, secondHalf) = proccessInput(entry)
            var repeatedPosition = -1
            for(letterIndex in firstHalf.indices){
                val letter = firstHalf[letterIndex]
                for(letterIndexSecond in secondHalf.indices){
                    val letterSecond = secondHalf[letterIndexSecond]
                    if(letter == letterSecond && repeatedPosition == -1){
                        repeatedPosition = letterIndex
                    }
                }
            }

            var letterValue = entry[repeatedPosition] - 'a' + 1
            if(letterValue < 0) letterValue += 'A'.code - 7

            letterValue
        }
    }

    fun calculateGroupLetter(group: List<String>): Int {
        var repeatedPosition = -1
        if(group.isEmpty()) return 0
        for(letterIndex in group[0].indices){
            val letter = group[0][letterIndex]
            for(letterIndexSecond in group[1].indices){
                val letterSecond = group[1][letterIndexSecond]
                for(letterIndexThird in group[2].indices) {
                    val letterThird = group[2][letterIndexThird]
                    if(letter == letterSecond && letter == letterThird){
                        repeatedPosition = letterIndex

                        var letterValue = group[0][repeatedPosition] - 'a' + 1
                        if(letterValue < 0) letterValue += 'A'.code - 7

                        return letterValue
                    }
                }
            }
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val group = arrayListOf<String>()
        input.forEachIndexed{ _, element ->
            group.add(element)
            if(group.size == 3) {
                sum += calculateGroupLetter(group)
                group.clear()
            }
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
