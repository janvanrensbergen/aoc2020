package be.moac.aoc.day05

import be.moac.aoc.readResource
import be.moac.aoc.timed
import kotlin.math.ceil

fun main() {
    val input = "/day05_input.txt".readResource()
    (0..10).forEach {
        BoardingPass.partOne(input)
        BoardingPass.partTwo(input)
    }
    timed { println("Part one: ${BoardingPass.partOne(input)}") }
    timed { println("Part two: ${BoardingPass.partTwo(input)}") }
}

object BoardingPass {

    fun partOne(input: String): Int {
        return input.lines()
            .asSequence()
            .filter { it.isNotBlank() }
            .map { it.substring(0 until 7) to it.substring(7 until 10) }
            .map { it.first.findRow() to it.second.findSeat() }
            .map { it.first * 8 + it.second }
            .max()!!
    }

    fun partTwo(input: String) = 0


    private fun String.findRow() =
        this
            .toCharArray()
            .fold(0..127) { range, char ->
                when (char) {
                    'F' -> range.first..range.last - range.divide()
                    'B' -> range.first + range.divide()..range.last
                    else -> range
                }
            }.first

    private fun String.findSeat() =
        this
            .toCharArray()
            .fold(0..7) { range, char ->
                when (char) {
                    'L' -> range.first..range.last - range.divide()
                    'R' -> range.first + range.divide()..range.last
                    else -> range
                }
            }.first

    private fun IntRange.divide() = ceil((this.last - this.first).toFloat().div(2)).toInt()

}
