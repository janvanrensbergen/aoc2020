package be.moac.aoc.day10

import be.moac.aoc.day09.EncodingError
import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input = "/day10_input.txt".readResource()
    repeat((0..10).count()) {
        Adapter.partOne(input)
        Adapter.partTwo(input)
    }
    timed { println("Part one: ${Adapter.partOne(input)}") }
    timed { println("Part two: ${Adapter.partTwo(input)}") }
}


object Adapter {

    fun partOne(input:String): Int {

        val result = input.parse()
            .sorted()
            .fold(0 to mutableMapOf(3 to 1)) { acc, i ->
                val difference = i - acc.first
                acc.second.merge(difference, 1) { a, b -> a + b }
                i to acc.second
            }.second

        return result[3]!! * result[1]!!
    }
    fun partTwo(input:String): Int = 0
}

private fun String.parse() =
    this.lines()
        .filter { it.isNotBlank() }
        .map { it.trim() }
        .map { it.toInt()}
