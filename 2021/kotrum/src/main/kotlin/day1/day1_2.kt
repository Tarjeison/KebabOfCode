package day1

import java.io.File

fun main() {
    val depthList = File("src/main/kotlin/day1/input.txt").readLines().map { it.toInt() }
    val nIncrease = depthList.windowed(3, 1).zipWithNext { a, b ->
        a.sum() < b.sum()
    }.count { it }

    print(nIncrease)
}
