package `Day-15`

import readInput
import java.util.PriorityQueue
import kotlin.math.floor

fun extractInputs(input: List<String>): List<List<Int>> {
    return input.map {it.trim().split("").filter{it.isNotEmpty()}.map { it.toInt() }.toList()}
}

data class Point(val i: Int, val j: Int)

fun dp(pt: Point, arr: List<List<Int>>,  cache: MutableMap<Point, Int>): Int {
    if(pt in cache){
        return cache.getValue(pt)
    }
    if(pt.i !in arr.indices || pt.j !in arr[0].indices) {
        return Int.MAX_VALUE
    }
    if(pt.i == arr.size-1 && pt.j == arr[0].size-1) {
        return arr[pt.i][pt.j]
    }

    cache[pt] = arr[pt.i][pt.j] + minOf(dp(Point(pt.i+1, pt.j), arr, cache), dp(Point(pt.i, pt.j+1), arr, cache))
    return cache.getValue(pt)
}

fun part1(input: List<String>): Int {
    val arr = extractInputs(input)
    val cache = mutableMapOf<Point, Int>().withDefault { 0 }
    return dp(Point(0, 0), arr, cache) - arr[0][0]
}

fun getDirectionOffsets() =  listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

fun dp2(pt: Point, arr: List<List<Int>>,  cache: MutableMap<Point, Int>): Int {
    if(pt in cache){
        return cache.getValue(pt)
    }
    if(getVal(pt, arr) == Int.MAX_VALUE) {
        return Int.MAX_VALUE
    }
    if(pt.i == arr.size*5-1 && pt.j == arr[0].size*5-1) {
        return getVal(pt, arr)
    }
    cache[pt] = getVal(pt, arr) + minOf(dp2(Point(pt.i+1, pt.j), arr, cache), dp2(Point(pt.i, pt.j+1), arr, cache))
    return cache.getValue(pt)
}

fun djikstra(arr: List<List<Int>>) : Int {
    val visited = mutableSetOf<Point>()
    val pq = PriorityQueue<Pair<Int, Point>>(compareBy {it.first})
    pq.add(0 to Point(0, 0))
    while(pq.isNotEmpty()) {
        val (dist, pt) = pq.poll()
        if(pt in visited) {
            continue
        }
        if(pt.i == arr.size*5-1 && pt.j == arr[0].size*5-1) {
            return dist
        }
        visited.add(pt)
        getDirectionOffsets().forEach { (xOff, yOff) ->
            val (x, y) = pt.i + xOff to pt.j + yOff
            val neigh = Point(x, y)
            val neighVal =  getVal(neigh, arr)
            if(neigh !in visited && neighVal != Int.MAX_VALUE) {
                pq.add(dist + neighVal to neigh)
            }
        }
    }
    return -1
}

fun getVal(pt: Point, arr: List<List<Int>>): Int {
    val (m,n ) = arr.size to arr[0].size
    val (xGrid,yGrid) = floor(pt.i.toDouble() / m).toInt() to floor(pt.j.toDouble() / n).toInt()
    val (i, j) = pt.i % m to pt.j % n
    if(xGrid !in 0..4 || yGrid !in 0..4) {
        return Int.MAX_VALUE
    }
    var xVal = arr[i][j] + xGrid
    xVal = if (xVal > 9) xVal - 9 else xVal
    var yVal = xVal + yGrid
    yVal = if (yVal > 9) yVal - 9 else yVal
    return yVal
}

fun part2(input: List<String>): Int {
    val arr = extractInputs(input)
    return djikstra(arr)
}

fun main() {
    var input = readInput("./Day-15/sample")
    //println(part1(input))
    println(part2(input))

    input = readInput("./Day-15/test")
    //println(part1(input))
    println(part2(input))
}