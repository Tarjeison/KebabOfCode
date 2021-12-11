package day4
import java.io.File

fun main(){
    var (numbers,boards) = getInput()
    //partTwo()

}

fun getInput(): Pair<List<Int>, List<Board>> {

    val rawlines = File("src/main/kotlin/day4/sample.txt").readLines()

    var numbers = rawlines[0].split(",").map { it.toInt() }
    var boardlines = rawlines.toMutableList()
    boardlines.removeAt(0)
    boardlines.removeAt(0)



    var boards = listOf<Board>()
    var board=0
    boardlines.forEach(){
        var rows = mutableListOf<Int>()
        if( it != ""){

        }
    }
    boards.forEach(){println(it)}
    return Pair(numbers,boards)
}

data class Board(val rows: List<Int>)
