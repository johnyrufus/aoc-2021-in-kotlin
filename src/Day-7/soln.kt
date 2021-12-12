package `Day-7`

import readInput
import java.lang.Math.*


fun main() {

    fun part1(input: List<String>): Int {
        var pos: List<Int> = input[0].split(",").map(String::toInt).toList()
        val (min, max) = Pair(pos.minOf{it}, pos.maxOf{it})
        return (min..max).minOf { cur ->
            pos.map {abs(cur - it)}.sum()
        }
    }

    fun part2(input: List<String>): Int {
        var pos: List<Int> = input[0].split(",").map(String::toInt).toList()
        val (min, max) = Pair(pos.minOf{it}, pos.maxOf{it})
        return (min..max).minOf { cur ->
            pos.map { val n = abs(cur - it); (n * (n+1))/2}.sum()
        }
    }

    val testInput = readInput("./Day-7/sample")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("./Day-7/test")
    println(part1(input))
    println(part2(input))
}



