package day7
import java.io.File
import kotlin.math.abs

fun main() {

    val input = File("src/main/kotlin/day7/input.txt").useLines { it.first() }.split(",").map{it.toInt()}
    val median = input.sorted()[input.size/2]
    val fuelSum = input.map{ abs(it-median)}.sum()
    println("Part 1: " +fuelSum)
    val mean = input.average().toInt()
    val betterFuelSum = input.map{ (0..abs(it-mean)).sum()}
    println("Part 2: " +betterFuelSum.sum() + " "+ mean )



}
