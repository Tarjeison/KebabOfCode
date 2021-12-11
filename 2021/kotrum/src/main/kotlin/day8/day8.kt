package day8

import java.io.File

fun main() {
    val entries = processEntries()
    part1(entries)
    part2(entries)
}

fun part1(entries: List<Entry>) {
    val nSimpleDigits = entries.sumOf { entry ->
        entry.outputValue.count { it.length in listOf(2, 3, 4, 7) }
    }
    println(nSimpleDigits)
}

fun part2(entries: List<Entry>) {
    var totalSum = 0
    entries.forEach { entry ->
        val codeValues = mutableMapOf<String, Int>()

        val one = entry.signalPatterns.first { it.length == 2 }
        codeValues[one] = 1

        val four = entry.signalPatterns.first { it.length == 4 }
        codeValues[four] = 4

        val seven = entry.signalPatterns.first { it.length == 3 }
        codeValues[seven] = 7

        val eight = entry.signalPatterns.first { it.length == 7 }
        codeValues[eight] = 8

        val three = entry.signalPatterns.filter { it.length == 5 }.first { fiveCharCode ->
            seven.count { fiveCharCode.contains(it) } == seven.length
        }
        codeValues[three] = 3

        val nine = entry.signalPatterns.filter { it.length == 6 }.first { nineCandidate ->
            four.count { nineCandidate.contains(it) } == four.length
                    && seven.count { nineCandidate.contains(it) } == seven.length
        }
        codeValues[nine] = 9

        val zero = entry.signalPatterns.first { zeroCandidate ->
            zeroCandidate.length == 6 && zeroCandidate != nine && one.count { zeroCandidate.contains(it) } == one.length
        }
        codeValues[zero] = 0

        val six = entry.signalPatterns.first { it.length == 6 && it !in listOf(zero, nine) }
        codeValues[six] = 6

        val five = entry.signalPatterns.first { fiveCandidate ->
            fiveCandidate.length == 5
                    && fiveCandidate !in codeValues.keys
                    && six.count { fiveCandidate.contains(it) } == 5
        }
        codeValues[five] = 5

        val two = entry.signalPatterns.first { it !in codeValues.keys }
        codeValues[two] = 2

        val sum = entry.outputValue.joinToString("") { code ->
            val key = codeValues.keys.filter { it.length == code.length }.first { number ->
                number.count { code.contains(it) } == number.length
            }
            codeValues[key].toString()
        }.toInt()
        totalSum += sum
    }
    println(totalSum)
}

fun processEntries(): List<Entry> {
    return File("src/main/kotlin/day8/input.txt").readLines().map {
        val patternsAndOutput = it.split(" | ")
        Entry(patternsAndOutput[0].split(" "), patternsAndOutput[1].split(" "))
    }
}

data class Entry(val signalPatterns: List<String>, val outputValue: List<String>)

fun String.allCombinations() {
    this.forEach { }
}