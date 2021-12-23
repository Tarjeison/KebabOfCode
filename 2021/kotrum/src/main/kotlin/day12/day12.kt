package day12

import java.io.File

fun main() {
    val input = File("src/main/kotlin/day12/test_input.txt").readLines()
    val graph = Graph<Cave>()
    val nodes = input.map {
        val splitLine = it.split("-")
        Pair(splitLine[0], splitLine[1])
    }
    nodes.forEach { relation ->
        graph.addEdge(relation.first.toCave(), relation.second.toCave())
    }
    // part1(graph)
    part2(graph)

}

fun part2(graph: Graph<Cave>) {
    val smoolCaves = graph.adjacencyMap.keys.filterIsInstance<Cave.SmallCave>().filter { it.name !in listOf("start", "end") }
    val results = smoolCaves.map {
        println(it)
        graph.findAllPathsTo(
            start = Cave.SmallCave("start"),
            end = Cave.SmallCave("end"),
            visited = mutableListOf("start"),
            currentPath = mutableListOf(Cave.SmallCave("start")),
            bonusSmallCave = it.name
        )
    }
    print(results)
    print(results.maxOf { it })
}

fun part1(graph: Graph<Cave>) {
    val t = graph.findAllPathsTo(
        start = Cave.SmallCave("start"),
        end = Cave.SmallCave("end"),
        visited = mutableListOf("start"),
        currentPath = mutableListOf(Cave.SmallCave("start"))
    )
    println(t)
}

sealed class Cave {
    abstract val name: String

    data class SmallCave(override val name: String) : Cave()
    data class BigCave(override val name: String) : Cave()
}

fun String.toCave(): Cave {
    return when {
        this.all { it.isUpperCase() } -> Cave.BigCave(this)
        else -> Cave.SmallCave(this)
    }
}

fun Graph<Cave>.findAllPathsTo(
    start: Cave,
    end: Cave,
    visited: MutableList<String>,
    currentPath: MutableList<Cave> = mutableListOf(),
    bonusSmallCave: String? = null
): Int {
    if (start.name == "end") {
        println(currentPath)
        return 1
    }
    if (start is Cave.SmallCave) visited.add(start.name)
    val nPaths = adjacencyMap[start]!!.toList().map { cave ->
        if (!visited.contains(cave.name) || ((cave.name == bonusSmallCave) && visited.count { it == cave.name } == 1)) {

            val newPath = currentPath.toMutableList()
            newPath.add(cave)
            findAllPathsTo(cave, end, visited.toMutableList(), newPath, bonusSmallCave)
        } else {
            0
        }
    }
    return nPaths.sum()
}

/**
 * [More info](https://www.geeksforgeeks.org/graph-and-its-representations/).
 */
class Graph<T> {
    val adjacencyMap: HashMap<T, HashSet<T>> = HashMap()

    fun addEdge(sourceVertex: T, destinationVertex: T) {
        // Add edge to source vertex / node.
        adjacencyMap
            .computeIfAbsent(sourceVertex) { HashSet() }
            .add(destinationVertex)
        // Add edge to destination vertex / node.
        adjacencyMap
            .computeIfAbsent(destinationVertex) { HashSet() }
            .add(sourceVertex)
    }

    override fun toString(): String = StringBuffer().apply {
        for (key in adjacencyMap.keys) {
            append("$key -> ")
            append(adjacencyMap[key]?.joinToString(", ", "[", "]\n"))
        }
    }.toString()
}
