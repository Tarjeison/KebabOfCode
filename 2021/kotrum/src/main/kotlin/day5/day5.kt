package day5

import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

fun main() {
    val lines = processInputToLines()
    part1(lines)
    part2(lines)
}

fun part1(lines: List<Line>) {
    val straightLines = lines.filter { it.x1 == it.x2 || it.y1 == it.y2 }
    val rows = max(straightLines.maxOf { it.x1 }, straightLines.maxOf { it.x2 }) + 1
    val columns = max(straightLines.maxOf { it.y1 }, straightLines.maxOf { it.y2 }) + 1
    val diagram = Diagram.create(rows, columns)
    straightLines.forEach {
        diagram.markPointsInLine(it)
    }
    println(diagram.nPointsWithTwoOrMoreOverlap())
}

fun part2(lines: List<Line>) {
    val rows = max(lines.maxOf { it.x1 }, lines.maxOf { it.x2 }) + 1
    val columns = max(lines.maxOf { it.y1 }, lines.maxOf { it.y2 }) + 1
    val diagram = Diagram.create(rows, columns)
    lines.forEach {
        diagram.markPointsInLine(it)
    }
    println(diagram.nPointsWithTwoOrMoreOverlap())
}

fun processInputToLines(): List<Line> {
    return File("src/main/kotlin/day5/input.txt").readLines().map { line ->
        val splitLine = line.split(" -> ")
        val (startCoordinates, endCoordinates) =
            Pair(splitLine[0].split(","), splitLine[1].split(","))
        Line(
            x1 = startCoordinates[0].toInt(),
            y1 = startCoordinates[1].toInt(),
            x2 = endCoordinates[0].toInt(),
            y2 = endCoordinates[1].toInt()
        )
    }
}

data class Line(val x1: Int, val x2: Int, val y1: Int, val y2: Int)

class Diagram(private val points: List<List<Point>>) {

    fun nPointsWithTwoOrMoreOverlap(): Int {
        return points.sumOf { row ->
            row.count {
                it.occurrences >= 2
            }
        }
    }

    fun markPointsInLine(line: Line) {
        when {
            line.x1 == line.x2 -> {
                markVerticalLine(line)
            }
            line.y1 == line.y2 -> {
                markHorizontalLine(line)
            }
            else -> {
                markDiagonalLine(line)
            }
        }
    }

    private fun markDiagonalLine(line: Line) {
        val xValues = if (line.x1 > line.x2) {
            (line.x2..line.x1).toList().reversed()
        } else {
            (line.x1..line.x2).toList()
        }

        val yValues = if (line.y1 > line.y2) {
            (line.y2..line.y1).toList().reversed()
        } else {
            (line.y1..line.y2).toList()
        }
        xValues.forEachIndexed { index, x ->
            val y = yValues[index]
            points[y][x].occurrences++
        }
    }

    private fun markHorizontalLine(line: Line) {
        val horizontalRange = (min(line.x1, line.x2)..max(line.x1, line.x2))
        horizontalRange.forEach {
            points[line.y1][it].occurrences++
        }
    }

    private fun markVerticalLine(line: Line) {
        val verticalRange = (min(line.y1, line.y2)..max(line.y1, line.y2))
        verticalRange.forEach {
            points[it][line.x1].occurrences++
        }
    }

    companion object {
        fun create(rows: Int, columns: Int): Diagram {
            val points = List(columns) { List(rows) { Point() } }
            return Diagram(points)
        }
    }
}

data class Point(var occurrences: Int = 0)