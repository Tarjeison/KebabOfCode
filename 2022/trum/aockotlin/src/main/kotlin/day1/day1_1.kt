package day1

import java.io.File

fun main() {


    val elfCarryList = File("src/inputs/day1.txt").readText().split("\n\n").map { elf ->
        elf.lines().map { it.toInt() }
    }

    part1(elfCarryList)
    part2(elfCarryList)
}


private fun part1(elfCarry: List<List<Int>>) {
    println(elfCarry.maxOf { it.sum() })
}

private fun part2(elfCarry: List<List<Int>>) {
    val totalCarrySorted = elfCarry.map { it.sum() }.sortedDescending()
    println(totalCarrySorted.take(3).sum())

}