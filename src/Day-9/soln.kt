package `Day-9`

import readInput

fun isLowPoint(i: Int, j: Int, arr:List<List<Int>>): Boolean {
    val (nRows, nCols) = Pair(arr.size, arr[0].size)
    return getDirectionOffsets().all {
        val (x,y) = Pair(i+it.first, j+it.second)
        x !in 0 until nRows || y !in 0 until  nCols || arr[i][j] < arr[x][y]
    }
}

fun getDirectionOffsets() =  listOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))

fun getArray(input: List<String>) = input.map {it.trim().split("").filter { it.isNotEmpty() }.map { it.toInt() }}.toList()

fun part1(input: List<String>): Int {
    val arr = getArray(input)
    return arr.indices.flatMap { i ->
        arr[0].indices.map { j ->
            when {
                isLowPoint(i, j, arr) -> 1 + arr[i][j]
                else -> 0
            }
        }
    }.sum()
}

fun getIslandSizeDfs(i: Int, j: Int, arr: List<List<Int>>, visited: MutableSet<Pair<Int, Int>>): Int {
    val (nRows, nCols) = Pair(arr.size, arr[0].size)
    return when {
        Pair(i, j) in visited || i !in 0 until nRows || j !in 0 until nCols || arr[i][j] == 9 -> 0
        else -> {
            visited.add(Pair(i, j))
            1 + getDirectionOffsets().sumOf { getIslandSizeDfs(i + it.first, j + it.second, arr, visited) }
        }
    }
}

fun part2(input: List<String>): Int {
    val arr = getArray(input)
    val visited = mutableSetOf<Pair<Int, Int>>()
    return arr.indices.flatMap { i ->
        arr[0].indices.map { j ->
            getIslandSizeDfs(i, j, arr, visited)
        }
    }.sortedDescending().slice(0..2).reduce { acc, i -> acc * i }
}

fun main() {
    var input = readInput("./Day-9/sample")
    println(part1(input))
    println(part2(input))

    input = readInput("./Day-9/test")
    println(part1(input))
    println(part2(input))
}



