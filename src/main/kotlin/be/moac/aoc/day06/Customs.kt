package be.moac.aoc.day06

import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input = "/day06_input.txt".readResource()
    (0..10).forEach {
        Customs.partOne(input)
        Customs.partTwo(input)
    }
    timed { println("Part one: ${Customs.partOne(input)}") }
    timed { println("Part two: ${Customs.partTwo(input)}") }
}


object Customs {

    fun partOne(input: String): Int {
        return input.parse()
            .map { it.toCharArray().distinct() }
            .map { it.size }
            .sum()
    }
    fun partTwo(input: String): Int = 0


    private fun String.parse(): List<String> =
        this.lineSequence()
            .fold(listOf("")) { result, line ->
                when {
                    line.isNotBlank() -> result.dropLast(1) + listOf("${result.last().trim()}${line.trim()}")
                    else -> result + listOf("")
                }
            }
}
