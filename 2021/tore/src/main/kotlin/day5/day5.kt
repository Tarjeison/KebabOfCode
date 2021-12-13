package day5
import java.awt.Point
import java.io.File
import kotlin.math.abs
import kotlin.math.max

fun main() {

    val input = File("src/main/kotlin/day5/input.txt").useLines { lines ->
        lines.map { line ->
            val points = line.split(" -> ", ",").map { it.toInt() }
            Line(points[0], points[1], points[2], points[3])
        }.toList()
    }
    var part1_list = listOf<Pair<Int, Int>> ()
    var part2_list = listOf<Pair<Int, Int>> ()
    input.forEach{
        part2_list += (it.toRange())
        if ((it.x1 == it.x2)||(it.y1 == it.y2)) {
            part1_list += (it.toRange())

        }
    }

    println("Part 1: " + part1_list.groupingBy { it }.eachCount().filterValues { it > 1 }.size)
    println("Part 2: " + part2_list.groupingBy { it }.eachCount().filterValues { it > 1 }.size)



}

data class Line(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int,
) {
    fun toRange(): List<Pair<Int, Int>> {
        val steps = max(abs(x2 - x1), abs(y2 - y1))
        val dx = if (x1 == x2) 0 else (x2 - x1) / abs(x2 - x1)
        val dy = if (y1 == y2) 0 else (y2 - y1) / abs(y2 - y1)
        return (0 .. steps).map { i -> (x1 + i * dx) to (y1 + i * dy) }
    }
}


