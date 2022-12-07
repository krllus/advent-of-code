fun main() {

    fun processInput(input: List<String>): ElfFileItem {
        val root = ElfFileItem(
            parentNode = null, name = "/", type = ElfFileType.DIRECTORY
        )
        var currentNode: ElfFileItem? = null
        var currentCommand: ElfCommand? = null
        var setCommandNow: Boolean
        input.forEach { line ->
            setCommandNow = false
            if (line.startsWith("$ cd ")) {
                setCommandNow = true
                currentCommand = ElfCommand.CHANGE_DIR
                if (currentNode == null) currentNode = root
                val elfFileName = line.split("\$ cd ").last()
                currentNode = when (elfFileName) {
                    "/" -> root
                    ".." -> currentNode?.parentNode
                    else -> currentNode?.findNode(elfFileName)
                }
            }

            if (line.startsWith("$ ls")) {
                setCommandNow = true
                currentCommand = ElfCommand.LIST_DIR
            }

            if (!setCommandNow && currentCommand == ElfCommand.LIST_DIR) {
                val (a, b) = line.split(" ")
                if (a == "dir") {
                    currentNode?.addChildren(
                        parent = currentNode, item = ElfFileItem(
                            type = ElfFileType.DIRECTORY, name = b
                        )
                    )
                } else {
                    val size = a.toIntOrNull() ?: error("not possible to parse size: $a")
                    currentNode?.addChildren(
                        parent = currentNode, item = ElfFileItem(
                            type = ElfFileType.FILE, name = b, size = size
                        )
                    )
                }
            }
        }

        return root
    }

    fun part1(input: List<String>): Int {
        val fileSystem = processInput(input)
        val fileSystemDirs = fileSystem.getDirs()

        return fileSystemDirs.filter { it.eligible(100_000) }.sumOf { it.getFullSize() }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")

    check(part1(testInput) == 95437)
    check(part2(testInput) == 0)

    val input = readInput("Day07")
    println(part1(input)) // 1444896
    println(part2(input))
}

enum class ElfCommand {
    CHANGE_DIR, LIST_DIR
}

data class ElfFileItem(
    val name: String,
    val type: ElfFileType,
    private val size: Int = 0,
    var parentNode: ElfFileItem? = null,
    val children: MutableList<ElfFileItem> = mutableListOf()
) {

    fun findNode(name: String): ElfFileItem? {
        val nodes = children.filter { it.name == name }
        return nodes.firstOrNull()
    }

    fun addChildren(parent: ElfFileItem?, item: ElfFileItem) {
        item.parentNode = parent
        children.add(item)
    }

    fun getFullSize(): Int {
        return when (type) {
            ElfFileType.FILE -> this.size
            ElfFileType.DIRECTORY -> this.size + children.sumOf { it.getFullSize() }
        }
    }

    fun eligible(maxSize: Int): Boolean {
        return this.getFullSize() <= maxSize
    }

    fun getDirs(): List<ElfFileItem> {
        val dirs = mutableListOf<ElfFileItem>()
        dirs.addAll(when (this.type) {
            ElfFileType.FILE -> emptyList()
            ElfFileType.DIRECTORY -> {
                val list = mutableListOf<ElfFileItem>()
                list.add(this)
                children.forEach { list.addAll(it.getDirs()) }
                return list
            }
        })
        return dirs
    }

    override fun toString(): String {
        return when (this.type) {
            ElfFileType.FILE -> "name: $name, size: ${getFullSize()}"
            ElfFileType.DIRECTORY -> "name: $name, childrenCount: ${children.size}, size: ${getFullSize()}"
        }
    }
}

enum class ElfFileType {
    DIRECTORY, FILE
}

