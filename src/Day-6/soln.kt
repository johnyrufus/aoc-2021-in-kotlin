package `Day-6`

import readInput
import java.lang.Math.*


fun main() {

    fun part12(input: List<String>): Long {
        var ages = input[0].split(",").map(String::toInt).groupBy{it}.mapValues { it.value.count().toLong() }.toMutableMap()
        (1..256).forEach {
            var newOnes = 0L
            newOnes = ages.getOrDefault(0, 0L)
            ages = ages.filter { it.key != 0 }. map { (age, count) ->
                age - 1 to count
            }.toMap().toMutableMap()
            if(newOnes > 0) {
                ages.put(8, newOnes)
                ages[6] = ages.getOrDefault(6, 0) + newOnes
            }
        }
        return ages.values.sum()
    }

    val testInput = readInput("./Day-6/sample")
    println(part12(testInput))

    val input = readInput("./Day-6/test")
    println(part12(input))
}



