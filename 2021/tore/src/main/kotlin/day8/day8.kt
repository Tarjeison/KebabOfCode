package day8
import java.io.File
import kotlin.math.abs

fun main() {
    val signals = mutableListOf<String>()
    val outputs = mutableListOf<String>()
    File("src/main/kotlin/day8/sample.txt").useLines { lines -> lines.forEach {
        val line = it.split(" | ")
        signals.add(line.first())
        outputs.add(line.last())
    }}

    var sortedOutputs = outputs.joinToString("") { it }


    var sumUnique = 0
    val known = listOf(2,3,4,7)
    outputs.forEach{ line ->
        line.split(" ").forEach {
            if(it.count() in known)sumUnique++
        }

    }
    println("Part 1: " + sumUnique)

    val valueMap = mapOf<String,Int>("cagedb" to 0, "ab" to 1, "gcdfa" to 2, "fbcad" to 3, "eafb" to 4, "acedgfb" to 8, "cdfbe" to 5, "dab" to 7, "cefabd" to 9, "cdfgeb" to 6, "eafb" to 4)

    outputs.forEach { println(it.split(" ")) }



    println("Part 2: " + valueMap["dab"] )
}

