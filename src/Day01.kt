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
        return (0 until input.size-3).map {
            when {
                input[it] + input[it+1] + input[it+2] < input[it+1] + input[it+2] + input[it+3] -> 1
                else -> 0
            }
        }.sum()
    }

    val testInput = readInput("Day01_test")
    println(part1(testInput))
    println(part2(testInput))

}
