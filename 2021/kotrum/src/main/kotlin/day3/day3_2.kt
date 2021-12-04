package day3

import java.io.File

fun main() {
    val input = File("src/main/kotlin/day3/input.txt").readLines()
    val nBits = input.first().length - 1

    val oxygenCandidates = input.toMutableList()
    val co2Candidates = input.toMutableList()

    for (i in 0..nBits) {
        updateCo2Candidates(i, co2Candidates)
        updateOxygenCandidates(i, oxygenCandidates)
    }
    println(oxygenCandidates.first().fromBinaryToDecimal() * co2Candidates.first().fromBinaryToDecimal())
}

fun updateOxygenCandidates(index: Int, currentCandidates: MutableList<String>) {
    val counts = currentCandidates.groupingBy { it[index] }.eachCount().toList().sortedBy { it.second }
    if (counts.size == 1) return
    val keepChar = if (counts.first().second == counts.last().second) {
        '1'
    } else {
        counts.last().first
    }
    currentCandidates.takeIf { it.size > 1 }?.removeIf { it[index] != keepChar }
}

fun updateCo2Candidates(index: Int, currentCandidates: MutableList<String>) {
    val counts = currentCandidates.groupingBy { it[index] }.eachCount().toList().sortedBy { it.second }
    val keepChar = if (counts.first().second == counts.last().second) {
        '0'
    } else {
        counts.first().first
    }
    currentCandidates.takeIf { it.size > 1 }?.removeIf { it[index] != keepChar }
}
