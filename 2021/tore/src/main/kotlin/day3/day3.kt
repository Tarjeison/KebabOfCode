package day3
import java.io.File

fun main(){
	partOne()
	partTwo()
	
}

fun partOne(){
	val inputList = mutableListOf<String>()
	File("src/main/kotlin/day3/input.txt").useLines{ lines -> lines.forEach { inputList.add(it) }}
    var bitSum = BSum(inputList)
    var gamma = ""
    var epsilon = ""
    bitSum.forEach { 
        if (it > inputList.size/2 ){
            gamma+="1"
            epsilon += "0"
        }else{
            epsilon+="1"
            gamma +="0"

        }
     }
    val result = gamma.toInt(2) * epsilon.toInt(2)
	println("Part 1: " + result)
}

fun partTwo(){
	val inputList = mutableListOf<String>()
	File("src/main/kotlin/day3/input.txt").useLines{ lines -> lines.forEach { inputList.add(it) }}
   
    var o2List = inputList.toMutableList()
    var bitSum = BSum(o2List)
    for (bitIndex in 0..(bitSum.size-1)) {
        var newSum = BSum(o2List)
        if (newSum[bitIndex]*2 >= o2List.size){
            for (value in inputList) {
                if ((value[bitIndex] == '0' )&&( o2List.size > 1 )){
                    o2List.remove(value)
                }
            }
        }else{
            for (value in inputList) {
                
                if (value[bitIndex] == '1'){
                    o2List.remove(value)
                }
            }
        }
     }
     var co2List = inputList.toMutableList()
     bitSum = BSum(co2List)
     for (bitIndex in 0..(bitSum.size-1)) {
         var newSum = BSum(co2List)
         if (newSum[bitIndex]*2 >= co2List.size){
             for (value in inputList) {
                 if ((value[bitIndex] == '1' )&&( co2List.size > 1 )){
                     co2List.remove(value)
                 }
             }
         }else{
             for (value in inputList) {
                 
                 if ((value[bitIndex] == '0')&&( co2List.size > 1 )){
                     co2List.remove(value)
                 }
             }
         }
      }
	println("Part 2: " + o2List[0].toInt(2) * co2List[0].toInt(2))
}

fun BSum(inputList: MutableList<String>):MutableList<Int>{
    var bitSum = mutableListOf<Int>()
    inputList[0].forEach { bitSum.add(0) }
    inputList.forEach {
        var i = 0
        for (bit in it){
            bitSum[i] += Character.getNumericValue(bit)
            i++
        }
    }
    return bitSum
}