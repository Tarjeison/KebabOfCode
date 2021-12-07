package day7

import java.io.File

fun main() {
    part1()
    part2()
}

// This guy... Misunderstood the task but this algorithm worked, which made me debug part 2 forever
fun part1() {
    val crabPositions = processCrabs()
    val distanceToOtherCrabs = crabPositions.mapIndexed { currentIndex, currentCrab ->
        var distanceToOtherCrabs = 0
        crabPositions.forEachIndexed { otherIndex, otherCrab ->
            if (otherIndex != currentIndex) {
                distanceToOtherCrabs += kotlin.math.abs(currentCrab - otherCrab)
            }
        }
        distanceToOtherCrabs
    }
    println(distanceToOtherCrabs.minOf { it })
}

fun part2() {
    val crabPositions = processCrabs()
    val distances = mutableListOf<Int>()
    for (i in crabPositions.minOf { it }..crabPositions.maxOf { it }) {
        var distanceToOtherCrabs = 0
        crabPositions.forEach { crabPos ->
            val nSteps = kotlin.math.abs(i - crabPos)
            val distance = (nSteps + 1) * nSteps / 2
            distanceToOtherCrabs += distance
        }
        distances.add(distanceToOtherCrabs)
    }
    println(distances.minOf { it })
}

fun processCrabs() =
    File("src/main/kotlin/day7/input.txt").readLines()[0].split(",").map { it.toInt() }