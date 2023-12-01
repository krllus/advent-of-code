fun main() {
    val number = 550
    val mask = "5xx"

    val result = blabla(number, mask)
    println(result)

    val number1 = 450
    val mask2 = "45x"

    val result2 = blabla(number1, mask2)
    println(result2)

}

fun blabla(
    number: Int,
    mask: String
): Boolean {

    val upper = String(
        mask.map { it ->
            if (it.isDigit()) it
            else '9'
        }.toCharArray()
    ).toInt()

    val lower = String(mask.map { it ->
        if (it.isDigit()) it
        else '0'
    }.toCharArray()).toInt()

    return number in lower..upper
}

