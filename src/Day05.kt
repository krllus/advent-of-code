fun main() {

    fun createStorage(cargos: List<String>): MutableMap<Int, ElfStack> {
        val storage: MutableMap<Int, ElfStack> = HashMap()

        for (index in 0..cargos.size - 2) {
            val row = cargos[index]
            val elements = row.toCharArray()
                .filterIndexed { elementIndex, _ ->
                    elementIndex % 4 == 1
                }

            elements.forEachIndexed { elementIndex, element ->
                var elfStack = storage[elementIndex]
                if (elfStack == null)
                    elfStack = ElfStack()
                elfStack.addElement(element, true)
                storage[elementIndex] = elfStack
            }
        }
        return storage
    }

    fun part1(text: String): String {
        val input = text.split("\n\n")
        val first = input[0]
        val cargos = first.split("\n")
        val storage: MutableMap<Int, ElfStack> = createStorage(cargos)

        val second = input[1]
        val commands = second.split("\n").toCommandList()

        for (command in commands) {
            repeat(command.quantity) {
                val shelfOrigin = storage[command.origin - 1] ?: error("shelf  [${command.origin}] not found")
                val shelfDestination = storage[command.destiny - 1] ?: error("shelf [${command.destiny}] not found")
                val element = shelfOrigin.removeElement()
                shelfDestination.addElement(element)
            }
        }

        val result = storage.values.map { it.topElement() }.joinToString(separator = "")
        println(result)
        return result
    }

    fun part2(text: String): String {
        val input = text.split("\n\n")
        val first = input[0]
        val cargos = first.split("\n")
        val storage: MutableMap<Int, ElfStack> = createStorage(cargos)

        val second = input[1]
        val commands = second.split("\n").toCommandList()

        for (command in commands) {

            val shelfOrigin = storage[command.origin - 1] ?: error("shelf  [${command.origin}] not found")
            val shelfDestination = storage[command.destiny - 1] ?: error("shelf [${command.destiny}] not found")

            val elements = arrayListOf<Char>()

            repeat(command.quantity) {
                elements.add(shelfOrigin.removeElement())
            }

            elements.reverse()

            for (element in elements) {
                shelfDestination.addElement(element)
            }
        }

        val result = storage.values.map { it.topElement() }.joinToString(separator = "")
        println(result)
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsText("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInputAsText("Day05")
    println(part1(input))
    println(part2(input))
}

class ElfStack() {
    private val elements = ArrayDeque<Char>()

    fun addElement(element: Char, first: Boolean = false) {
        if (element == ' ')
            return
        if (first) {
            elements.addFirst(element)
        } else {
            elements.addLast(element)
        }
    }

    fun removeElement(): Char {
        return elements.removeLast()
    }

    fun topElement(): Char {
        return elements.lastOrNull() ?: ' '
    }

}

fun List<String>.toCommandList(): List<Command> {
    return this.map { it.toCommand() }
}

fun String.toCommand(): Command {
    val inputs = this.split(" ")
    return Command(
        quantity = inputs[1].toInt(),
        origin = inputs[3].toInt(),
        destiny = inputs[5].toInt(),
    )
}

data class Command(
    val quantity: Int,
    val origin: Int,
    val destiny: Int
)