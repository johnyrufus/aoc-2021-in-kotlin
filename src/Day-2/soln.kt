package `Day-2`

import readInput


fun main() {
    fun part1(input: List<String>): Int {
        val pairs = input.map {
            val (cmd, value) = it.split(" ")
            val intVal = value.toInt()
            when(cmd) {
                "up" -> Pair(0, -intVal)
                "down" -> Pair(0, intVal)
                else -> Pair(intVal, 0)
            }
        }
        return pairs.map{it.first}.sum() * pairs.map{it.second}.sum()
    }

    fun part2(input: List<String>): Int {
        val (pos, _, depth) = input.fold(Triple(0, 0, 0)) { acc, cur ->
            val (pos, aim, depth) = acc
            val (cmd, value) = cur.split(" ")
            val intVal = value.toInt()
            when(cmd) {
                "up" -> Triple(pos, aim-intVal, depth)
                "down" -> Triple(pos, aim+intVal, depth)
                else -> Triple(pos+intVal, aim, depth + aim * intVal)
            }
        }
        return pos * depth
    }

    val testInput = readInput("./Day-2/sample")
    println(part1(testInput))

    val input = readInput("./Day-2/test")
    println(part1(input))
    println(part2(input))

}
