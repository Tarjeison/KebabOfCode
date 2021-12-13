package day9
import java.io.File
import kotlin.math.abs

fun main() {
    val input = mutableListOf<List<Int>>()
    File("src/main/kotlin/day9/sample.txt").useLines{ lines -> lines.forEach { input.add(it.map { eachInt -> Character.getNumericValue(eachInt) }) }}

    var lowPoints = listOf<Int>()

    for( x in 0..(input.size-1)){
        for( y in 0..(input.first().size-1)){
            var current = input[x][y]
            var right = input[x].getOrElse(y+1){ 10 }.toInt()
            var left = input[x].getOrElse(y-1){ 10 }.toInt()
            if(y == 0){left=10}
            if(y == input.first().size-1){right=10}
            var down = 10
            var up = 10
            if(x == 0){
                down = input[x+1][y]
            }else{
                up = input[x-1][y]
            }
            if(x == input.size-1){
                up = input[x-1][y]
            }else{
                down = input[x+1][y]
            }

            if((current < right) && (current < down) && (current < up) && (current < left)){
                lowPoints += current
            }
        }
    }

    println("Part 1:" + lowPoints.map { it+1 }.sum())
    println("Part 2: " )
}

