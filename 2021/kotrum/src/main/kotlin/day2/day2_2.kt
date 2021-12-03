package day2

fun main() {
    val commands = processInput()

    var horizontalPosition = 0
    var verticalPosition = 0
    var aim = 0

    commands.forEach { command ->
        when (command.direction) {
            Direction.UP -> aim -= command.units
            Direction.DOWN -> aim += command.units
            Direction.FORWARD -> {
                horizontalPosition += command.units
                verticalPosition += command.units*aim
            }
        }
    }

    print(verticalPosition * horizontalPosition)
}