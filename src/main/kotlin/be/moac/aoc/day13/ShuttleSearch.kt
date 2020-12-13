package be.moac.aoc.day13

import be.moac.aoc.readResource
import be.moac.aoc.timed
import kotlin.math.abs

fun main() {
    val input = "/day13_input.txt".readResource()
    repeat((0..10).count()) {
        ShuttleSearch.partOne(input)
        ShuttleSearch.partTwo(input)
    }
    timed { println("Part one: ${ShuttleSearch.partOne(input)}") }
    timed { println("Part two: ${ShuttleSearch.partTwo(input)}") }
}

object ShuttleSearch {

    fun partOne(input: String): Int {
        val filter = input.lines().filter { it.isNotBlank() }
        val timestamp = filter[0].trim().toInt()
        val busses = filter[1].split(',')
            .map { it.trim() }
            .mapNotNull { it.toIntOrNull() }
            .sorted()


        val result = busses
            .map { bus -> bus to bus * ((timestamp / bus) + 1) }
            .minBy { abs(timestamp - it.second) }!!

        return (result.second - timestamp) * result.first
    }

    fun partTwo(input: String): Int = 0

}
