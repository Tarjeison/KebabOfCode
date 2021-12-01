
import java.io.File
import java.io.BufferedReader

//val inputpasswords = listOf("1-3 a: abcde","1-13 b: cdefg","2-9 c: ccccccccc")

fun main(){
    val inputfile = "input.txt"
    //Create mutable list object
    val inputpasswords = mutableListOf<String>()
    //Create BufferedReader
    val bufferedReader = File(inputfile).bufferedReader()
    //Read each line into list object, with int casting
    bufferedReader.useLines { lines -> lines.forEach { inputpasswords.add(it) } }
    println("Valid passwords: "+ part1(inputpasswords))
    println("Really Valid passwords: "+ part2(inputpasswords))
}

fun part1(inputpasswords: MutableList<String>): Int{
    var totalValid = 0;

    for ( line in 0 until inputpasswords.size){
        val str = inputpasswords[line]
        var key = str[str.indexOf(":") - 1]
        var lowerlim = str.substringBefore(delimiter = "-").toInt()
        var upperlim = str.substringBefore(delimiter = " ").substringAfter(delimiter = "-").toInt()
        val password  = str.substringAfter(delimiter = ": ")
        val count = password.count { it == key.toChar() }
        if(count in lowerlim..upperlim)totalValid=totalValid+1;
    }
    return totalValid
}
fun part2(inputpasswords: MutableList<String>): Int{
    var totalValid = 0;
    for ( line in 0 until inputpasswords.size){
        val str = inputpasswords[line]
        var key = str[str.indexOf(":") - 1]
        var lowerlim = str.substringBefore(delimiter = "-").toInt()
        var upperlim = str.substringBefore(delimiter = " ").substringAfter(delimiter = "-").toInt()
        val password  = str.substringAfter(delimiter = ": ")
        if ( password[lowerlim-1 ] == key || password[upperlim-1]== key) {
            if (password[upperlim-1] != password[lowerlim-1]){
                totalValid=totalValid+1;
            }
        }
        
        
        
    }
    return totalValid
}