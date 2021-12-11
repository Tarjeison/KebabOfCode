package day10
import java.io.File
import kotlin.math.abs

fun main() {
    val input = mutableListOf<String>()
    File("src/main/kotlin/day10/input.txt").useLines { lines ->
        lines.forEach {
            input.add(it)
        }
    }
    var p1sum = 0
    var p2sum = mutableListOf<Long>()
    val incomplete = input.toMutableList()
    input.forEach {
        p1sum += corruptScore(it)
        if (corruptScore(it)>0){incomplete.remove(it)}
    }
    println("Part 1: " + p1sum)
    incomplete.forEach {
        p2sum.add(completeScore(it))
    }
    println("Part 2: " + p2sum.sorted()[p2sum.size/2])
}

val openChars = listOf<Char>('(','[','{','<')
val illegalScore = mapOf<Char,Int>(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137
)
val autocompleteScore = mapOf<Char,Int>(
    ')' to 1,
    ']' to 2,
    '}' to 3,
    '>' to 4
)

fun completeScore(line: String): Long {
    var current = mutableListOf<Char>()
    line.forEach{
        if(it in openChars){current.add(it)}
        else{
            if( it == oppositeChar(current.last())){current.removeLast()}
        }
    }
    var sum=0L
    current.reversed().forEach {
        sum *= 5
        sum += autocompleteScore.getValue(oppositeChar(it))
    }
    return sum
}

fun corruptScore(line: String): Int {
    var current = mutableListOf<Char>()
    line.forEach{
        if(it in openChars){current.add(it)}
        else{
            if( it == oppositeChar(current.last())){current.removeLast()}
            else{return illegalScore.getValue(it)}
        }
    }
    return 0
}

fun oppositeChar(char: Char): Char{
    when (char) {
        '(' -> return ')'
        '[' -> return ']'
        '{' -> return '}'
        '<' -> return '>'
        ')' -> return '('
        ']' -> return '['
        '}' -> return '{'
        '>' -> return '<'
        else -> return ' '
    }
}