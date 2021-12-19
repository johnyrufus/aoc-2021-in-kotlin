package `Day-11`

import readInput

fun getMatrix(input: List<String>): List<MutableList<Int>> {
    return input.map { it.trim().split("").filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()}.toList()
}

fun getNeighOffsets(): List<Pair<Int, Int>> {
    return (-1..1).flatMap { x ->
        (-1..1).mapNotNull {  y ->
            if(!(x == 0 && y == 0)) {
                x to y
            } else null
        }
    }
}


fun bfs(arr: List<MutableList<Int>>): Int {
    val queue = mutableListOf<Pair<Int, Int>>()
    var flashes = 0
    arr.indices.forEach{ i ->
        arr[0].indices.forEach { j ->
            if(arr[i][j] > 9) {
                queue.add(i to j)
            }
        }
    }
    while(queue.isNotEmpty()) {
        val (i, j) = queue.removeFirst()
        arr[i][j] = 0
        flashes += 1
        getNeighOffsets().forEach { (xOff, yOff) ->
            val (x,y) = i+xOff to j+yOff
            if(x in arr.indices && y in arr[0].indices && arr[x][y] in 1..9) {
                arr[x][y] += 1
                if (arr[x][y] > 9) {
                    queue.add(x to y)
                }
            }
        }
    }
    return flashes
}

fun part1(input: List<String>): Int {
    val arr = getMatrix(input)
    var res = 0
    (0 until 100).forEach {
        arr.indices.forEach{ i ->
            arr[0].indices.forEach { j ->
                arr[i][j] += 1
            }
        }
        res += bfs(arr)
    }
    return res
}

fun part2(input: List<String>): Int {
    val arr = getMatrix(input)
    var step = 0
    while(true) {
        step += 1
        arr.indices.forEach{ i ->
            arr[0].indices.forEach { j ->
                arr[i][j] += 1
            }
        }
        bfs(arr)
        if(arr.flatten().all { it == 0 }){
            return step
        }
    }
}

fun main() {
    var input = readInput("./Day-11/sample")
    println(part1(input))
    println(part2(input))

    input = readInput("./Day-11/test")
    println(part1(input))
    println(part2(input))
}



