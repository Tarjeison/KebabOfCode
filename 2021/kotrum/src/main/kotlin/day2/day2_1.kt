package day2

import java.io.File

fun main() {
    val commands = processInput()
    val forwardUnits = commands.filter { it.direction == Direction.FORWARD }.sumOf { it.units }
    val upwardUnits = commands.filter { it.direction == Direction.UP }.sumOf { it.units }
    val downwardUnits = commands.filter { it.direction == Direction.DOWN }.sumOf { it.units }

    val verticalPosition = downwardUnits - upwardUnits
    print(verticalPosition * forwardUnits)
}

fun processInput(): List<Command> {
    return File("src/main/kotlin/day2/input.txt").readLines().map { commandInput ->
        val command = commandInput.split(" ")
        val direction = Direction.valueOf(command.first().uppercase())
        val units = command.last().toInt()
        Command(direction, units)
    }
}

data class Command(val direction: Direction, val units: Int)

enum class Direction {
    UP, DOWN, FORWARD
}