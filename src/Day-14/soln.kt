package `Day-14`

import readInput
import kotlin.math.ceil

fun extractInputs(input: List<String>): Pair<String, Map<String, String>> {
    var template = input[0]
    val rules = input.slice(2 until input.size).map { it.split("->").let {it[0].trim() to it[1].trim()} }.toMap()
    return Pair(template, rules)
}

fun part1(input: List<String>): Int {
    var (template, rules) = extractInputs(input)
    repeat(10) {
        template = template.windowed(2).map { if(it in rules) it[0].toString()+rules[it] else it[0].toString()  }.joinToString("")+template.last()
    }
    val map = template.groupingBy { it }.eachCount()
    return map.maxOf { it.value } - map.minOf { it.value }
}


fun part2(input: List<String>): Long {
    val (template, rules) = extractInputs(input)
    val rulesPair = rules.mapValues { (key, value) ->
        Pair(key[0].toString() + value, value + key[1].toString())
    }.toMap()
    var counts: MutableMap<String, Long> = template.windowed(2).groupBy {it}.mapValues {(k,value) -> value.size.toLong()}.toMutableMap()
    repeat(40) {
        //println(counts)
        counts = counts.flatMap {(key, count: Long) ->
            when(key) {
                in rulesPair -> listOfNotNull(rulesPair[key]?.first to count, rulesPair[key]?.second to count)
                else -> listOf(key to count)
            }
        }.groupBy ({it.first!!}, {it.second}).mapValues { (k, v) -> v.sum() }.toMutableMap()
    }
    counts = counts.map{ (k,v) ->  k[0].toString() to v}.groupBy ({it.first}, {it.second}).mapValues { (k,v) -> v.sum() }.toMutableMap()
    counts[template.last().toString()] = counts.getOrDefault(template.last().toString(), 0) + 1
    return counts.maxOf { it.value } - counts.minOf { it.value }
}

fun main() {
    var input = readInput("./Day-14/sample")
    //println(part1(input))
    //println(part2(input))

    input = readInput("./Day-14/test")
    println(part1(input))
    println(part2(input))
}



