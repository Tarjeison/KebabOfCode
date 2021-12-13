package day9

import java.io.File

fun main() {
    val heights = readHeightMap()
    part1(heights)
    part2(heights)
}

fun part1(heights: List<List<Int>>): List<Index> {
    val lowPoints = mutableListOf<Int>()
    val lowPointIndices = mutableListOf<Index>()
    heights.forEachIndexed { columnIndex, row ->
        row.forEachIndexed { rowIndex, value ->
            val top = heights.getValueAt(columnIndex-1, rowIndex)
            val left = heights.getValueAt(columnIndex, rowIndex-1)
            val right = heights.getValueAt(columnIndex, rowIndex+1)
            val bottom = heights.getValueAt(columnIndex+1, rowIndex)
            val adjacentValues = listOf(top, left, right, bottom)
            if (adjacentValues.count { it > value } == adjacentValues.size) {
                lowPoints.add(value)
                lowPointIndices.add(Index(x = rowIndex, y = columnIndex))
            }
        }
    }
    val result = lowPoints.sumOf { it + 1 }
    println(result)
    return lowPointIndices
}

fun part2(heights: List<List<Int>>) {
    val lowPoints = part1(heights)
    val heightPoints = heights.map { it.map { Point(it) } }
    val basins = lowPoints.map { index ->
        heightPoints.forEach { it.forEach { it.counted = false } }
        1 + countBasinElementsAtIndex(index, heightPoints)
    }.sortedDescending()
    println(basins[0] * basins[1] * basins[2])
}

fun countBasinElementsAtIndex(index: Index, heights: List<List<Point>>): Int {
    val top = Pair(Index(index.x, index.y-1),heights.getValueAt(index.y-1, index.x))
    val left = Pair(Index(index.x-1, index.y), heights.getValueAt(index.y, index.x-1))
    val right = Pair(Index(index.x+1, index.y), heights.getValueAt(index.y, index.x+1))
    val bottom = Pair(Index(index.x, index.y+1), heights.getValueAt(index.y+1, index.x))
    val currentHeight = try {
        heights[index.y][index.x].value
    } catch (e: IndexOutOfBoundsException) {
        return 0
    }
    val higherPoints = listOf(top, left, right, bottom).filter { it.second != null }.map { point ->
        if (point.second!!.value in (currentHeight + 1)..8 && !point.second!!.counted) {
            heights[point.first.y][point.first.x].counted = true
            1 + countBasinElementsAtIndex(point.first, heights)
        } else {
            0
        }
    }.sumOf { it }
    return higherPoints
}

fun List<List<Int>>.getValueAt(columnIndex: Int, rowIndex: Int): Int {
    return try {
        this[columnIndex][rowIndex]
    } catch (e: IndexOutOfBoundsException) {
        Int.MAX_VALUE
    }
}

fun <T> List<List<T>>.getValueAt(columnIndex: Int, rowIndex: Int): T? {
    return try {
        this[columnIndex][rowIndex]
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun readHeightMap(): List<List<Int>> {
    return File("src/main/kotlin/day9/input.txt").readLines().map {
        it.split("").filter { it.isNotEmpty() }.map { it.toInt() }
    }
}

data class Index(val x: Int, val y: Int)
data class Point(val value: Int, var counted: Boolean = false)