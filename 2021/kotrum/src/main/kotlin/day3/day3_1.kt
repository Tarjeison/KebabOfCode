package day3

import java.io.File
import kotlin.math.pow

fun main() {
    part1()
}

fun part1() {
    val input = File("src/main/kotlin/day3/input.txt").readLines()
    val nBits = input.first().length - 1

    var highestBits = ""
    var lowestBits = ""

    for (i in 0..nBits) {
        val counts = input.groupingBy { it[i] }.eachCount().toList().sortedBy { it.second }
        lowestBits += counts.first().first
        highestBits += counts.last().first
    }
    val gammaRate = highestBits.fromBinaryToDecimal()
    val epsilonRate = lowestBits.fromBinaryToDecimal()
    print(gammaRate * epsilonRate)
}

fun String.fromBinaryToDecimal() : Int {
    var sum = 0
    this.reversed().forEachIndexed {
            k, v -> sum += v.toString().toInt() * pow(2, k)
    }
    return sum
}

fun pow(base: Int, exponent: Int) = base.toDouble().pow(exponent.toDouble()).toInt()


