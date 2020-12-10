package be.moac.aoc.day10

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

    fun partOne(input: String): Long {
        val adapters = input.parse()
        val result = (adapters + listOf(adapters.max()!! + 3L))
            .sorted()
            .fold(0L to mutableMapOf<Long, Long>()) { acc, i ->
                val difference = i - acc.first
                acc.second.merge(difference, 1) { a, b -> a + b }
                i to acc.second
            }.second

        return result[3]!! * result[1]!!
    }

    fun partTwo(input: String): Long {
        val parsed = input.parse().sorted()
        val adapters = listOf(0L) + parsed + listOf(parsed.max()!! + 3L)
        return adapters
            .fold<Long, MutableList<MutableList<Long>>>(mutableListOf(mutableListOf())) { acc, l ->
                val last = acc.last()
                last.lastOrNull()?.let { if (l - it > 1) acc.add(mutableListOf(l)) else last.add(l) } ?: last.add(l)
                acc
            }
            .map {
                when (it.size) {
                    1 -> 1L
                    2 -> 1L
                    3 -> 2L
                    4 -> 4L
                    5 -> 7L
                    else -> it.size.toLong()
                }
            }
            .reduce { acc, i -> acc * i }
    }
}

private fun String.parse() =
    this.lines()
        .filter { it.isNotBlank() }
        .map { it.trim() }
        .map { it.toLong() }
