package be.moac.aoc.day09

import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input = "/day09_input.txt".readResource()
    repeat((0..10).count()) {
        EncodingError.partOne(input)
        EncodingError.partTwo(input)
    }
    timed { println("Part one: ${EncodingError.partOne(input)}") }
    timed { println("Part two: ${EncodingError.partTwo(input)}") }
}


object EncodingError {

    fun partOne(input: String, value: Int = 25): Long =
        with(input.parse()) {
            (value until this.size)
                .filter {
                    this.subList(it - value, it).check(this[it])
                }
                .map { this[it] }
                .first()
        }

    fun partTwo(input: String): Int = 0

}

private fun List<Long>.check(sum: Long) =
    this.filterIndexed { index, a ->
        this.subList(index + 1, this.size)
            .any { b -> a + b == sum }
    }.isEmpty()

private fun String.parse() =
    this
        .lines()
        .filter { it.isNotBlank() }
        .map { it.trim().toLong() }

