package `Day-3`

import readInput


fun main() {

    fun List<String>.mostCommonBitByPos(pos: Int): Int {
        val n = this.size
        val ones = this.count { str ->
            str[pos] == '1'
        }
        return when {
            ones >= (n / 2.0) -> 1
            else -> 0
        }
    }

    fun List<String>.leastCommonBitByPos(pos: Int): Int = this.mostCommonBitByPos(pos).let { if(it == 1) 0 else 1 }

    fun part1(input: List<String>): Int {
        val most = input[0].indices.map {input.mostCommonBitByPos(it)}.joinToString("").toInt(2)
        val least = input[0].indices.map {input.leastCommonBitByPos(it)}.joinToString("").toInt(2)
        return most * least
    }

    fun List<String>.reduceBy(func: List<String>.(Int) -> Int): Int {
        var filtered = this
        var pos = 0
        while(filtered.size > 1) {
            filtered = filtered.filter {
                filtered.func(pos) == Character.getNumericValue(it[pos])
            }
            pos++
        }
        return filtered[0].toInt(2)
    }

    fun part2(input: List<String>): Int {
        return input.reduceBy(List<String>::mostCommonBitByPos) * input.reduceBy(List<String>::leastCommonBitByPos)
    }


    val testInput = readInput("./Day-3/sample")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("./Day-3/test")
    println(part1(input))
    println(part2(input))

}

