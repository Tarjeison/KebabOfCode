package day1

import java.io.File

fun main() {
    val depthList = File("src/main/kotlin/day1/input.txt").readLines().map { it.toInt() }
    print(depthList.windowed(2, 1, false).count { it.first() < it.last() })
}