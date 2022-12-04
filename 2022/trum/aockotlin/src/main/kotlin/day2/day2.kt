package day2

import java.io.File

fun main() {


    val input = File("src/inputs/day2.txt").readLines()
    part1(input)
    part2(input)

}

private fun part1(input: List<String>) {
    val matchPairings = input.map {
        val moves = it.split(" ")
        Match(Choice.createFromLetter(moves[0]), Choice.createFromLetter(moves[1]))
    }


    println(matchPairings.map { it.getScore() }.sum())
}

private fun part2(input: List<String>) {
    val matchPairings = input.map {
        val moves = it.split(" ")
        FixedMatch(Choice.createFromLetter(moves[0]), Outcome.fromLetter(moves[1]))
    }


    println(matchPairings.map { it.getScore() }.sum())
}

data class Match(val opponent: Choice, val me: Choice) {
    fun getScore(): Int {
        return me.fight(opponent) + me.points
    }
}

data class FixedMatch(val opponent: Choice, val me: Outcome) {
    fun getScore(): Int {
        return when (me) {
            Outcome.Draw -> {
                Outcome.Draw.points + opponent.points
            }
            Outcome.Lose -> {
                Outcome.Lose.points + opponent.winsTo.points
            }
            Outcome.Win -> {
                Outcome.Win.points + opponent.losesTo.points
            }
        }
    }
}

sealed interface Outcome {
    val points: Int
    object Draw: Outcome {
        override val points: Int
            get() = 3
    }

    object Win: Outcome {
        override val points: Int
            get() = 6
    }

    object Lose: Outcome {
        override val points: Int
            get() = 0
    }

    companion object {
        fun fromLetter(letter: String): Outcome {
            return when (letter) {
                "X" -> Lose
                "Y" -> Draw
                "Z" -> Win
                else -> throw IllegalArgumentException("Invalid input")
            }
        }
    }
}


sealed interface Choice {
    val points: Int
    val winsTo: Choice
    val losesTo: Choice
    fun fight(other: Choice): Int
    object Rock: Choice {

        override val points: Int
            get() = 1
        override val winsTo: Choice
            get() = Scissors
        override val losesTo: Choice
            get() = Paper

        override fun fight(other: Choice): Int {
            return when (other) {
                Paper -> 0
                Rock -> 3
                Scissors -> 6
            }
        }
    }

    object Paper: Choice {

        override val points: Int
            get() = 2
        override val winsTo: Choice
            get() = Rock
        override val losesTo: Choice
            get() = Scissors

        override fun fight(other: Choice): Int {
            return when (other) {
                Paper -> 3
                Rock -> 6
                Scissors -> 0
            }
        }
    }

    object Scissors: Choice {

        override val points: Int
            get() = 3
        override val winsTo: Choice
            get() = Paper
        override val losesTo: Choice
            get() = Rock

        override fun fight(other: Choice): Int {
            return when (other) {
                Paper -> 6
                Rock -> 0
                Scissors -> 3
            }
        }
    }

    companion object {
        fun createFromLetter(letter: String): Choice {
            return when (letter) {
                "A", "X" -> Rock
                "B", "Y" -> Paper
                "C", "Z" -> Scissors
                else -> throw IllegalArgumentException("Invalid input")
            }
        }
    }
}


