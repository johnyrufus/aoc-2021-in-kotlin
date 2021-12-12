package `Day-8`

import readInput
import java.lang.Math.*

fun main() {
    fun part1(input: List<String>): Int {
        val codes = input.flatMap { it.split("|")[1].trim().split(" ").map{it.trim()}}
        return codes.count{it.length in setOf(2,3,4,7)}
    }

    fun String.split2(delimiter: String): Pair<String, String> {
        val splits = this.split(delimiter)
        return Pair(splits[0].trim(), splits[1].trim())
    }

    fun common(a: String?, b:String?) : Int {
        val s1 = a?.split("")?.filter{ it.isNotBlank()}
        val s2 = b?.split("")?.filter{ it.isNotBlank()}
        return s1?.intersect(s2!!)?.size!!
    }

    fun String.sorted() = String(toCharArray().apply { sort() })

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val (patterns, code) = line.split2("|")
            //println("$patterns, $code")
            val pMap = mutableMapOf<String, String>()
            patterns.split(" ").sortedBy{it.length}.forEach {  pattern ->
                val p = pattern.sorted()
                pMap.plusAssign( when(p.length) {
                    2 -> "1" to p
                    3 -> "7" to p
                    4 -> "4" to p
                    7 -> "8" to p
                    5 -> if(common(pMap["1"], p) == 2) {
                        "3" to p
                    } else if(common(pMap["4"], p) == 2) {
                        "2" to p
                    } else {
                        "5" to p
                    }
                    else -> if(common(pMap["1"], p) == 1) {
                        "6" to p
                    } else if(common(pMap["3"], p) == 4) {
                        "0" to p
                    } else {
                        "9" to p
                    }
                })
            }
            val reversed = pMap.entries.associateBy({ it.value }) { it.key }
            code.split(" ").map {reversed[it.sorted()]}.joinToString("").toInt()
        }
    }

    var input = readInput("./Day-8/sample")
    println(part1(input))
    println(part2(input))

    input = readInput("./Day-8/test")
    println(part1(input))
    println(part2(input))
}



