package `Day-10`

import readInput

val opens = setOf('{', '<', '(', '[')
val closesToOpens = mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
val openToCloses = closesToOpens.entries.map { it.value to it.key}.toMap()
val points = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
val points2 = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

fun firstOffendingAndStack(line: String): Pair<Char?, List<Char>?> {
    val stack = mutableListOf<Char>()
    line.toCharArray().forEach { ch ->
        when(ch) {
            in opens -> stack.add(ch)
            else -> {
                when {
                    (stack.isEmpty() || stack.last() != closesToOpens[ch]) -> return ch to null
                    else -> stack.removeLast()
                }
            }
        }
    }
    return null to stack
}

fun part1(input: List<String>): Int {
    return input.mapNotNull { firstOffendingAndStack(it).first }.sumOf { points.getOrDefault(it, 0)}
}

fun getClosingPoints(input: List<Char>): Long = input.reversed().fold (0) { acc, ch ->  acc * 5 + points2.getOrDefault(openToCloses[ch], 0) }

fun part2(input: List<String>): Long {
    return input.map{firstOffendingAndStack(it).second}.filterNotNull().map {getClosingPoints(it)}.sorted().let { it.get(it.size/2) }
}

fun main() {
    var input = readInput("./Day-10/sample")
    println(part1(input))
    println(part2(input))

    input = readInput("./Day-10/test")
    println(part1(input))
    println(part2(input))
}



