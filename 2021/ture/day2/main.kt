package main
import java.io.File

fun main(){
	partOne()
	partTwo()
	
}

fun partOne(){
	val inputList = mutableListOf<String>()
	File("day2/input.txt").useLines{ lines -> lines.forEach { inputList.add(it) }}
    val instructionList = mutableListOf<Pair<String,Int>>()
    inputList.forEach{
        instructionList.add(Pair(it.split(" ")[0],it.split(" ")[1].toInt()))
    }
    var depth = 0
    var horizontal = 0
    instructionList.forEach{
        val move = it.second
        when(it.first){
            "forward" -> horizontal += move
            "down" -> depth += move
            "up" -> depth -= move
            else -> {println("error in input")}
        }
    }
    println("Part 1: " + (depth*horizontal))
}

fun partTwo(){
	val inputList = mutableListOf<String>()
	File("day2/input.txt").useLines{ lines -> lines.forEach { inputList.add(it) }}
    val instructionList = mutableListOf<Pair<String,Int>>()
    inputList.forEach{
        instructionList.add(Pair(it.split(" ")[0],it.split(" ")[1].toInt()))
    }
    var depth = 0
    var horizontal = 0
    var aim = 0
    instructionList.forEach{
        val move = it.second
        when(it.first){
            "forward" -> {
                horizontal += move
                depth += move*aim
            }
            "down" -> aim += move
            "up" -> aim -= move
            else -> {println("error in input")}
        }
    }
    println("Part 2: " + (depth*horizontal))
}