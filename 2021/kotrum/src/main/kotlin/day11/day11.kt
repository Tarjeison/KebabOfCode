package day11

import java.io.File
import java.lang.IndexOutOfBoundsException

fun main() {
    val input = File("src/main/kotlin/day11/input.txt").readLines().map { row ->
        row.map {
            Octopus(it.digitToInt())
        }
    }
    // part1(input)
    part2(input)
}

fun part1(input: List<List<Octopus>>) {
    val octopusGrid = OctopusGrid(input)
    repeat(100) {
        octopusGrid.increment()
    }
    println(octopusGrid.nFlashes)
}

fun part2(input: List<List<Octopus>>) {
    val octopusGrid = OctopusGrid(input)
    var increments = 0
    while (!octopusGrid.isFullFlash()) {
        octopusGrid.increment()
        increments++
    }

    println(increments)
}

class OctopusGrid(
    val octopuses: List<List<Octopus>>,
    var nFlashes: Int = 0
) {
    fun increment() {
        octopuses.forEach {
            it.forEach { octopus ->
                print(octopus.energyLevel)
                octopus.reset()
            }
            println()
        }
        println()
        for (column in octopuses.indices) {
            for (row in octopuses[column].indices) {
                nFlashes += incrementOctopusAt(row, column)
            }
        }
    }

    fun isFullFlash(): Boolean {
        return octopuses.all { column ->
            column.all { it.energyLevel == 0 }
        }
    }

    private fun incrementOctopusAt(rowIndex: Int, columnIndex: Int): Int {
        var nFlashes = 0
        try {
            octopuses[columnIndex][rowIndex].incrementEnergy()
            if (octopuses[columnIndex][rowIndex].shouldCountFlash()) {
                nFlashes++
                nFlashes += incrementOctopusAt(columnIndex = columnIndex - 1, rowIndex =  rowIndex - 1)
                nFlashes += incrementOctopusAt(columnIndex = columnIndex - 1, rowIndex = rowIndex)
                nFlashes += incrementOctopusAt(columnIndex = columnIndex - 1, rowIndex = rowIndex + 1)
                nFlashes += incrementOctopusAt(columnIndex = columnIndex, rowIndex = rowIndex - 1)
                nFlashes += incrementOctopusAt(columnIndex = columnIndex, rowIndex = rowIndex + 1)
                nFlashes += incrementOctopusAt(columnIndex = columnIndex + 1, rowIndex = rowIndex - 1)
                nFlashes += incrementOctopusAt(columnIndex = columnIndex + 1, rowIndex = rowIndex)
                nFlashes += incrementOctopusAt(columnIndex = columnIndex + 1, rowIndex = rowIndex + 1)
            }
        } catch (e: IndexOutOfBoundsException) {
            // This is fine :)))
        }
        return nFlashes
    }
}

data class Octopus(var energyLevel: Int,
                   private var hasFlashed: Boolean = false,
                   private var flashCounted: Boolean = false) {
    fun incrementEnergy() {
        if (energyLevel == 9) {
            energyLevel = 0
            hasFlashed = true
        } else {
            if (!hasFlashed) energyLevel++
        }
    }

    fun shouldCountFlash(): Boolean {
        return if (hasFlashed && !flashCounted) {
            flashCounted = true
            true
        } else {
            false
        }
    }

    fun reset() {
        hasFlashed = false
        flashCounted = false
    }
}