package main
import java.io.File

fun main(){
	partOne()
	partTwo()
	
}

fun partOne(){
	val inputList = mutableListOf<Int>()
	File("day1/input.txt").useLines{ lines -> lines.forEach { inputList.add(it.toInt()) }}
	
	var sum=0
	var previous = inputList[0]
	for (item in inputList){
		if (item > previous){
			sum ++
		}
		previous = item

	}
	println("Part 1: " + sum)
}

fun partTwo(){
	val inputList = mutableListOf<Int>()
	File("day1/input.txt").useLines{ lines -> lines.forEach { inputList.add(it.toInt()) }}
	
	val newValues = mutableListOf<Int>()

	for(i in 0..(inputList.size-3) ){
		newValues.add(inputList[i]+inputList[i+1]+inputList[i+2])
	}
	var sum=0
	var previous = newValues[0]
	newValues.forEach{
		if (it > previous){
			sum ++
		}
		previous = it
	}
	println("Part 2: " + sum)
}