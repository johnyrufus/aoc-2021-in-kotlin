package `Day-12`

import readInput

fun getGraph(input: List<String>): Map<String, Set<String>> {
    return input.flatMap {it.trim().split("-").let { listOf(it[0] to it[1], it[1] to it[0])}}.groupBy { it.first }.mapValues { (_, v) -> v.map {it.second}.toSet() }.toMap()
}

var numPaths:Int = 0

fun dfs(node: String, visited: MutableSet<String>, graph: Map<String, Set<String>>) {
    if(node == "end") {
        numPaths += 1
        return
    }
    graph[node]?.filter { (it.first().isUpperCase() || it !in visited) && it != "start"}?.forEach { neigh ->
        visited.add(neigh)
        dfs(neigh, visited, graph)
        visited.remove(neigh)
    }
}

fun part1(input: List<String>): Int {
    val graph = getGraph(input)
    dfs("start", mutableSetOf(), graph)
    return numPaths
}

fun dfs2(node: String, visited: MutableSet<String>, graph: Map<String, Set<String>>, isSmallTwice: Boolean) {
    if(node == "end") {
        numPaths += 1
        return
    }

    graph[node]?.filter {it != "start"}?.forEach { neigh ->
        var curSmallTwice = false
        if(neigh.first().isLowerCase() && neigh in visited && !isSmallTwice) {
            curSmallTwice = true
        }
        if(neigh.first().isUpperCase() || neigh !in visited || curSmallTwice) {
            visited.add(neigh)
            dfs2(neigh, visited, graph, isSmallTwice || curSmallTwice)
            if(!curSmallTwice) {
                visited.remove(neigh)
            }
        }
    }
}

fun part2(input: List<String>): Int {
    val graph = getGraph(input)
    dfs2("start", mutableSetOf(), graph, false)
    return numPaths
}

fun main() {
    var input = readInput("./Day-12/sample")
    //println(part1(input))
    //println(part2(input))

    input = readInput("./Day-12/test")
    //println(part1(input))
    println(part2(input))
}



