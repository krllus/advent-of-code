package year_2022

import utils.readInputAsText

fun main() {

    fun getMonkeys(monkeysStr: List<String>): Map<Int, Monkey> {
        return buildMap {
            monkeysStr.forEachIndexed { index, s ->

                // split by line and remove first "Monkey n"
                val lines = s.split("\n").drop(1)

                val items = lines[0].split(":").last()
                    .split(",")
                    .map {
                        BagItem(it.trim().toLong())
                    }
                val itemsStack = ArrayDeque<BagItem>()
                itemsStack.addAll(items)

                val operation = lines[1].split("new = old ").last()
                val test = lines[2].split("by ").last().toInt()

                val monkeySuccess = lines[3].takeLast(1).toInt()
                val monkeyFailure = lines[4].takeLast(1).toInt()

                val monkey = Monkey(
                    id = index,
                    bagItems = itemsStack,
                    operation = operation,
                    test = test,
                    testSuccessMonkeyId = monkeySuccess,
                    testFailureMonkeyId = monkeyFailure
                )
                put(index, monkey)
            }
        }
    }

    fun part1(input: String): Int {
        val monkeysStr = input.split("\n\n")
        val monkeys: Map<Int, Monkey> = getMonkeys(monkeysStr)

        repeat(20) {
            monkeys.values.forEach { monkey ->
                val monkeySuccess = monkeys[monkey.testSuccessMonkeyId]
                    ?: error("monkey success[${monkey.testSuccessMonkeyId} not found")
                val monkeyFailure = monkeys[monkey.testFailureMonkeyId]
                    ?: error("monkey failure[${monkey.testFailureMonkeyId} not found")
                monkey.inspectItems(monkeySuccess, monkeyFailure)
            }
            monkeys.values.forEach { println(it) }
            println()
        }

        val monkeysItemCount = monkeys.values.map { it.inspectedItem }.sortedDescending().take(2)

        return monkeysItemCount.reduce { acc, i ->
            acc * i
        }
    }

    fun part2(input: String): Long {
        val monkeysStr = input.split("\n\n")
        val monkeys: Map<Int, Monkey> = getMonkeys(monkeysStr)

        repeat(10000) {
            monkeys.values.forEach { monkey ->
                val monkeySuccess = monkeys[monkey.testSuccessMonkeyId]
                    ?: error("monkey success[${monkey.testSuccessMonkeyId} not found")
                val monkeyFailure = monkeys[monkey.testFailureMonkeyId]
                    ?: error("monkey failure[${monkey.testFailureMonkeyId} not found")
                monkey.inspectItems(monkeySuccess, monkeyFailure)
                monkeys.values.forEach { println(it) }
                println()
            }
        }

        val monkeysItemCount = monkeys.values.map { it.inspectedItem }.sortedDescending().take(2)

        return monkeysItemCount[0].toLong() * monkeysItemCount[1].toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsText("Day11_test")
//    check(part1(testInput) == 10605)
//    check(part2(testInput) == 2713310158)

    val input = readInputAsText("Day11")
//    println(part1(input))
    println(part2(input))
}

data class Monkey(
    val id: Int,
    var inspectedItem: Int = 0,
    val bagItems: ArrayDeque<BagItem>,
    val operation: String,
    val test: Int,
    val testSuccessMonkeyId: Int,
    val testFailureMonkeyId: Int
) {

    fun inspectItems(monkeySuccess: Monkey, monkeyFailure: Monkey) {
        while (bagItems.isNotEmpty()) {
            inspectedItem++
            val currentItem = bagItems.removeFirst().copy()
            currentItem.worry(this.operation)
            currentItem.relief()
            if (testItem(currentItem.copy())) {
                monkeySuccess.receiveItem(currentItem)
            } else {
                monkeyFailure.receiveItem(currentItem)
            }
        }
    }

    private fun testItem(item: BagItem): Boolean {
        return item.value % test.toLong() == 0L
    }

    private fun receiveItem(bagItem: BagItem) {
        this.bagItems.addLast(bagItem)
    }

    override fun toString(): String {
        return "Monkey $id: ${bagItems.joinToString(separator = ",") { it.value.toString() }}"
    }
}

data class BagItem(
    var value: Long,
) {
    fun relief() {
        this.value = this.value % 9699690L
    }

    fun worry(operation: String) {
        val op = operation.split(" ").first()
        val operationValue = operation.split(" ").last().toLongOrNull()
        this.value = when (op) {
            "+" -> {
                if (operationValue == null) {
                    this.value + this.value
                } else {
                    this.value + operationValue
                }
            }

            "*" -> {
                if (operationValue == null) {
                    this.value * this.value
                } else {
                    this.value * operationValue
                }
            }

            else -> error("operation ($op) not parsable")
        }
    }
}



