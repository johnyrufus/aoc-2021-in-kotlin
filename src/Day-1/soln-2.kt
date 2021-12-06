package `Day-1`

import readInput


fun main() {
    fun part1(input: List<String>): Int {
        return input.zipWithNext { a, b ->
            when {
                a.toInt() < b.toInt() -> 1
                else -> 0
            }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val input = input.map{it.toInt()}
        return input.windowed(3).zipWithNext { a, b ->
            when {
                a.sum() < b.sum() -> 1
                else -> 0
            }
        }.sum()
    }

    val testInput = readInput("./Day-1/test")
    println(part1(testInput))
    println(part2(testInput))

}
