package `Day-5`

import readInput
import java.lang.Math.*

data class Point(val start:Int, val end:Int)
data class Line(val start: Point, val end:Point)

fun main() {
    fun getLines(input: List<String>): List<Line> {
        return input.map {
            val splits = it.split("->")
            val points = splits.map {it.split(",").map{ it.trim().toInt()}}. map {Point(it[0], it[1])}
            Line(points[0], points[1])
        }
    }

    fun getNums(start: Int, end:Int, size:Int): List<Int> {
        val res = when {
            start < end -> start .. end
            else -> start downTo end
        }.toList()
        return if(res.size < size) MutableList(size) { res[0] } else res
    }

    fun MutableMap<Point, Int>.inc(key: Point) {
        this[key] = this.getOrDefault(key, 0) + 1
    }

    fun part2(input: List<String>): Int {
        val lines = getLines(input)
        val countMap = mutableMapOf<Point, Int>().withDefault { 0 }
        lines.forEach { line ->
            val (point1, point2) = Pair(line.start, line.end)
            val size = max(abs(point1.start - point2.start), abs(point1.end - point2.end)) + 1
            val xs = getNums(point1.start, point2.start, size)
            val ys = getNums(point1.end, point2.end, size)
            xs.zip(ys).map {
                countMap.inc(Point(it.first, it.second))
            }
        }
        return countMap.count {it.value >= 2}
    }

    val testInput = readInput("./Day-5/sample")
    println(part2(testInput))

    val input = readInput("./Day-5/test")
    println(part2(input))

}


