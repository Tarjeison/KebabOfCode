package day10

import java.io.File

private val validOpenings = listOf('(', '[', '{', '<')
private val validClosures = listOf(')', ']', '}', '>')
private val brackets = mapOf(
    ('(' to ')'),
    ('[' to ']'),
    ('{' to '}'),
    ('<' to '>'),
)
private val scores = mapOf(
    (')' to 3),
    (']' to 57),
    ('}' to 1197),
    ('>' to 25137),
)

private val missingScores = mapOf(
    (')' to 1),
    (']' to 2),
    ('}' to 3),
    ('>' to 4),
)

fun main() {
    val input = File("src/main/kotlin/day10/input.txt").readLines()
    val corruptClosures = mutableMapOf(
        (')' to 0),
        (']' to 0),
        ('}' to 0),
        ('>' to 0),
    )
    val missingBracketsScores = mutableListOf<Long>()
    input.forEach { line ->
        val chunks = mutableListOf<Chunk>()
        line.forEach CharLoop@ { char ->
            if (validOpenings.contains(char)) {
                chunks.add(Chunk(closingBracket = brackets[char]!!))
            } else if (validClosures.contains(char)) {
                if (chunks.last().closingBracket == char) {
                    chunks.removeLast()
                } else {
                    corruptClosures[char] = corruptClosures[char]!! + 1
                    return@forEach
                }
            } else {
                throw IllegalStateException("Unknown bracket $char")
            }
        }
        var missingBracketSum = 0L
        chunks.reversed().forEach {
            missingBracketSum *= 5
            missingBracketSum += missingScores[it.closingBracket]!!
        }
        missingBracketsScores.add(missingBracketSum)

    }
    val points = validClosures.sumOf { scores[it]!! * corruptClosures[it]!! }
    println(points)
    val missingPoints = missingBracketsScores.sorted()[missingBracketsScores.size / 2]
    println(missingPoints)
}

data class Chunk(val closingBracket: Char)