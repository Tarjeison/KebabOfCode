
import java.io.File
import java.io.BufferedReader


fun main(){
    val inputfile = "input.txt"
    //Create mutable list object
    val input = mutableListOf<Int>()
    //Create BufferedReader
    val bufferedReader = File(inputfile).bufferedReader()
    //Read each line into list object, with int casting
    bufferedReader.useLines { lines -> lines.forEach { input.add(it.toInt()) } }
    
    
    part1(input)
    part2(input)
    
}

fun part1(input: MutableList<Int>){
    for ( i in 0 until input.size){
        for (j in 1 until input.size){
            if( input[i] + input[j] == 2020 ){
                println(input[i].toString() + " + " +  input[j].toString())
                println("Solution Part 1: " + (input[i] * input[j]).toString())
                return 
            }
        }
    }
}

fun part2(input: MutableList<Int>){
    for ( i in 0 until input.size){
        for (j in 1 until input.size){
            for (k in 2 until input.size){
                if( input[i] + input[j] + input[k] == 2020 ){
                    println(input[i].toString() + " + " +  input[j].toString() + " + " +  input[k].toString())
                    println("Solution Part 2: " + (input[i] * input[j] * input[k]).toString())
                    return
                }
            }
        }
    }
}