package `Day-4`

import readInput

fun isWinner(board: List<List<Int>>, seen: Set<Int>): Boolean {
    val rowResult = board.map {
        it.all(seen::contains)
    }.any { it }
    val colResult = board[0].indices.map { col ->
        board.indices.all { row -> board[row][col] in seen }
    }.any { it }
    return rowResult || colResult
}

fun getBoards(input: List<String>): List<List<List<Int>>> {
    return input.subList(1, input.size)
        .filter { it.isNotBlank() }
        .map {it.trim().split("\\s+".toRegex()).map(String::toInt)}
        .filterNot { it.isEmpty() }
        .chunked(5).toList()
}

fun part1(input: List<String>): Int {
    val order = input[0].split(",").map{it.toInt()}
    val boards = getBoards(input)
    val seen = mutableSetOf<Int>()
    order.forEach { num ->
        seen.add(num)
        boards
            .filter { isWinner(it, seen) }
            .firstOrNull()
            ?.let {
                return num * it.flatten().filterNot(seen::contains).sum()
            }
    }
    return 0
}

fun part2(input: List<String>): Int {
    val order = input[0].split(",").map{it.toInt()}
    val boards = getBoards(input)
    val seen = mutableSetOf<Int>()
    val result = mutableListOf<Int>()
    order.forEach { num ->
        seen.add(num)
        val passed = boards.indices.filter { !result.contains(it) && isWinner(boards[it], seen) }
        result.addAll(passed)
        if(result.size == boards.size) {
            return num * boards[result.last()].flatten().filterNot(seen::contains).sum()
        }
    }
    return 0
}

fun main() {
    val testInput = readInput("./Day-4/sample")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("./Day-4/test")
    println(part1(input))
    println(part2(input))
}
