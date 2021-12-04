package day4

import java.io.File
import java.lang.IllegalStateException

fun main() {
    part1()
    part2()
}

fun part1() {
    val bingoGame = processInput()
    while (true) {
        val result = bingoGame.playNormal()
        if (result != null) {
            println(result)
            return
        }
    }
}

fun part2() {
    val bingoGame = processInput()
    while (true) {
        val result = bingoGame.playSquidGame()
        if (result != null) {
            println(result)
            return
        }
    }
}

fun processInput(): BingoGame {
    val input = File("src/main/kotlin/day4/input.txt").readLines()
    val drawnNumbers = input[0].split(",").map { it.toInt() }
    val boards = input
        .slice(1 until input.size)
        .filter { it != "" }
        .map { line ->
            line.split(" ")
                .filter { it != "" }
        }
        .chunked(5)
        .map { board ->
            Board(
                board.map { row ->
                    row.map { Square(it.toInt()) }
                }
            )
        }
    return BingoGame(drawnNumbers, boards)
}

class BingoGame(private val drawnNumbers: List<Int>, private val boards: List<Board>) {
    private var iteration = 0

    // Part 1
    fun playNormal(): Int? {
        if (iteration == drawnNumbers.size) throw IllegalStateException("Ran out of numbers :/")
        val currentNumber = drawnNumbers[iteration]
        boards.forEach { board ->
            board.registerNumber(currentNumber)
            if (board.isBingo()) {
                return currentNumber * board.sumOfUnmarkedNumbers()
            }
        }
        iteration++
        return null
    }

    //Part 2
    fun playSquidGame(): Int? {
        if (iteration == drawnNumbers.size) throw IllegalStateException("Ran out of numbers :/")
        val currentNumber = drawnNumbers[iteration]
        boards.forEach { board ->
            board.registerNumber(currentNumber)
            if (board.isBingo() && boards.count { it.hasBingo } == boards.size) {
                return currentNumber * board.sumOfUnmarkedNumbers()
            }
        }
        iteration++
        return null
    }
}

class Board(private val squares: List<List<Square>>) {

    var hasBingo = false

    fun registerNumber(number: Int) {
        squares.forEach { row ->
            row.forEach { square ->
                if (square.number == number) square.isDrawn = true
            }
        }
    }

    fun isBingo(): Boolean {
        val nSquares = squares.first().size - 1
        for (i in 0..nSquares) {
            val horisontalBingo = squares[i].none { !it.isDrawn }
            val verticalBingo = squares.groupBy { it[i] }.none { !it.key.isDrawn }
            if (horisontalBingo || verticalBingo) {
                hasBingo = true
                return true
            }
        }
        return false
    }

    fun sumOfUnmarkedNumbers(): Int {
        return squares.flatten().filter { !it.isDrawn }.sumOf { it.number }
    }
}

data class Square(val number: Int, var isDrawn: Boolean = false)
