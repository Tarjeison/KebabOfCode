
import java.io.File
import java.io.BufferedReader

//val map = listOf("1-3 a: abcde","1-13 b: cdefg","2-9 c: ccccccccc")

fun main(){
    val inputfile = "input.txt"
    //Create mutable list object
    val map = mutableListOf<String>()
    //Create BufferedReader
    val bufferedReader = File(inputfile).bufferedReader()
    //Read each line into list object, with int casting
    bufferedReader.useLines { lines -> lines.forEach { map.add(it) } }
    
    println("Trees in map: " + getTrees(map, 1, 1)) // 60
    println("Trees in map: " + getTrees(map, 3, 1)) // 225
    println("Trees in map: " + getTrees(map, 5, 1)) // 225
    println("Trees in map: " + getTrees(map, 7, 1)) // 225
    println("Trees in map: " + getTrees(map, 1, 2)) // 225
    println("Total: "+ (getTrees(map, 1, 1)*getTrees(map, 3, 1)*getTrees(map, 5, 1)*getTrees(map, 7, 1)*getTrees(map, 1, 2)))
}

fun getTrees(map: MutableList<String>, right: Int, down: Int ): Int{
    var nTrees = 0;
    var index = 0;
    var rows = map.size;
    var row = 0;
    while ( row < rows){
        val str = map[row]
        if(str[index] == '#')nTrees ++
        index += right
        row += down
        if (index >= str.length)index = index-str.length
    }
    return nTrees;
}