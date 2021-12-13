package day6
import java.awt.Point
import java.io.File
import kotlin.math.abs
import kotlin.math.max

fun main() {

    val input = File("src/main/kotlin/day6/input.txt").useLines { it.first() }.split(",").map{it.toInt()}
    var days = MutableList(9,{ it*0L})
    input.forEach(){days[it]++}
    println(days)
    var newFish = 0L
    for (i in 1..256){
        newFish = days[0]
        for( v in 0..7){days[v] = days[v+1]}
        days[8] = newFish
        days[6]+=newFish
        if (i==80){println("Part 1: " + days.sum())}
    }
    println("Part 2: " + days.sum())

}
