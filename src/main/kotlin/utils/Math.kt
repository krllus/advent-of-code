package utils

fun main() {
    val a = 1701
    val b = 3768

    println(gcd(a, b))
    println(gcd(b, a))
    println(gcd(b, b))
    println(gcd(3, 5))
    println(gcd(5, 3))

    println(lcm(5, 3))
}

// greatest common divisor
fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a
    else gcd(b, a % b)
}

// least common multiple
fun lcm(a: Int, b: Int): Int = (a * b) / gcd(a, b)

fun sieve(n: Int): IntArray {
    val oddNumbers = IntArray(n) { 1 }
    oddNumbers[0] = 0
    oddNumbers[1] = 0
    for (i in 2..n + 1) {
        if (oddNumbers[i] == 1)
            for (j in i * i..n + 1 step i) {
                oddNumbers[j] = 0
            }
    }
    return oddNumbers
}

// https://facompetindo.gitbook.io/programacao-competitiva/python/soma_max_intervalo