package `Day-9`

import readInput
import java.lang.Math.*

fun main() {
    fun isLow(i: Int, j: Int, arr:List<List<Int>>, value:Int): Boolean {
        val (nRows, nCols) = Pair(arr.size, arr[0].size)
        return i !in 0 until nRows || j !in 0 until  nCols || value < arr[i][j]
    }

    fun part1(input: List<String>): Int {
        val arr = input.map {it.trim().split("").filter { it.isNotEmpty() }.map { it.toInt() }}.toList()
        return arr.indices.flatMap { i ->
            arr[0].indices.map { j ->
                listOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0)).all {
                    isLow(i+it.first, j+it.second, arr, arr[i][j])
                }.let { if(it) arr[i][j]+1 else 0 }
            }
        }.sum()
    }


    var input = readInput("./Day-9/sample")
    println(part1(input))
    //println(part2(input))

    input = readInput("./Day-9/test")
    println(part1(input))
    //println(part2(input))
}



