package `Day-13`

import readInput

data class Point(val x: Int, val y: Int)
enum class FoldType { x, y }
data class Fold(val type: FoldType, val value: Int)

fun getInputs(input: List<String>): Pair<Set<Point>, List<Fold>> {
    val points = input.takeWhile { it.contains(",") }.map { it.split(",").let { Point(it[0].toInt(), it[1].toInt()) } }.toSet()
    val folds = input.takeLastWhile { it.contains("fold") }
        .map { it.split("=").let { Fold(FoldType.valueOf(it[0].last().toString()), it[1].toInt()) } }
    return Pair(points, folds)
}

fun applyFold(points: Set<Point>, fold: Fold): Set<Point> {
    return when(fold.type) {
        FoldType.x -> {
            points.mapNotNull { (x, y) ->
                when {
                    x < fold.value -> Point(x, y)
                    x > fold.value -> Point(x - 2 *(x - fold.value), y)
                    else -> null
                }
            }
        }
        FoldType.y -> {
            points.mapNotNull { (x, y) ->
                when {
                    y < fold.value -> Point(x, y)
                    y > fold.value -> Point(x, y - 2 *(y - fold.value))
                    else -> null
                }
            }
        }
    }.toSet()
}

fun part1(input: List<String>): Int {
    val (points, folds) = getInputs(input)
    val resPoints = (applyFold(points, folds[0]))
    return resPoints.size
}


fun part2(input: List<String>) {
    var (points, folds) = getInputs(input)
    folds.forEach { fold ->
        points = applyFold(points, fold)
    }
    val (maxX, maxY) = Pair(points.maxOf{it.x}, points.maxOf{it.y})
    val graph = (0 until maxY+1).map { MutableList(maxX+1){'.'} }
    points.forEach { (x, y) ->
        graph[y][x] = '#'
    }
    graph.forEach { println(it)}
}

fun main() {
    var input = readInput("./Day-13/sample")
    println(part1(input))
    println(part2(input))

    input = readInput("./Day-13/test")
    println(part1(input))
    println(part2(input))
}



