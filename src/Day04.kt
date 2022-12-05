fun main() {


    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach {
            val entry = it.split(",")
            val first = entry.first()
            val second = entry.last()

            if (first.isContained(second) || second.isContained(first)) {
                sum++
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        input.forEach {
            val entry = it.split(",")
            val first = entry.first()
            val second = entry.last()

            if (first.overlaps(second)) {
                sum++
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

fun String.isContained(value: String): Boolean {

    val asdf = this.split("-")
    val fdsa = value.split("-")

    val thisInit = asdf.first().toInt()
    val thisEnd = asdf.last().toInt()

    val init = fdsa.first().toInt()
    val end = fdsa.last().toInt()

    return thisInit <= init && thisEnd >= end
}

fun String.overlaps(value: String): Boolean {

    val a = this.split("-")
    val b = value.split("-")

    val aI = a.first().toInt()
    val aE = a.last().toInt()

    val bI = b.first().toInt()
    val bE = b.last().toInt()

    // sort by greater init
    return if(aI <= bI){
        bI <= aE
    }else{
        aI <= bE
    }

}