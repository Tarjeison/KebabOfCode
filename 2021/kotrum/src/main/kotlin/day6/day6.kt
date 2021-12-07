package day6

import day3.pow
import java.io.File

fun main() {
    part1()
    part2()
}

fun part1() {
    val population = LanternFishPopulation(processInput().toMutableList())
    repeat(80) {
        population.iterateDay()
    }
    println(population.getNLanternFish())
}

// Ok mr. advent of efficient code who won't let me use brain-dead list implementation
fun part2() {
    val fishPopulation = MutableList(9) { 0L }
    val initialPopulation = processInput()
    initialPopulation.forEach { fishy ->
        fishPopulation[fishy.daysUntilNextBebe]++
    }
    repeat(256) {
        val newBebes = fishPopulation[0]
        for (i in 0 until fishPopulation.size - 1) {
            fishPopulation[i] = fishPopulation[i+1]
        }
        fishPopulation[fishPopulation.size -1] = newBebes
        fishPopulation[6] += newBebes
    }
    println(fishPopulation.sum())
}

fun processInput(): List<LanternFish> {
    return File("src/main/kotlin/day6/input.txt")
        .readLines()[0]
        .split(",")
        .map {
            LanternFish(it.toInt())
        }
}

class LanternFishPopulation(private val population: MutableList<LanternFish>) {

    fun getNLanternFish() = population.size

    fun iterateDay() {
        val newBebes = mutableListOf<LanternFish>()
        population.forEach { preggyFish ->
            when (preggyFish.daysUntilNextBebe) {
                0 -> {
                    preggyFish.daysUntilNextBebe = 6
                    newBebes.add(LanternFish(8))
                }
                in 1..8 -> {
                    preggyFish.daysUntilNextBebe--
                }
                else -> throw IllegalStateException("PreggyFish to late to labour ${preggyFish.daysUntilNextBebe}")
            }
        }
        population.addAll(newBebes)
    }
}

data class LanternFish(var daysUntilNextBebe: Int)