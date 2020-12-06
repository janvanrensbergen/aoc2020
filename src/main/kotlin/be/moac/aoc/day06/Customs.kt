package be.moac.aoc.day06

import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input = "/day06_input.txt".readResource()
    repeat((0..10).count()) {
        Customs.partOne(input)
        Customs.partTwo(input)
    }
    timed { println("Part one: ${Customs.partOne(input)}") }
    timed { println("Part two: ${Customs.partTwo(input)}") }
}


object Customs {

    fun partOne(input: String): Int = input.parseGroups()
        .map { it.answers.joinToString("") }
        .map { it.toCharArray().distinct() }
        .map { it.size }
        .sum()


    fun partTwo(input: String): Int =
        input.parseGroups()
            .asSequence()
            .map { it.uniques }
            .filter { it.isNotBlank() }
            .map { it.toCharArray().distinct() }
            .map { it.size }
            .sum()

    private fun String.parseGroups(): List<Group> =
        this.lineSequence()
            .fold((listOf(Group()))) { result, line ->
                when {
                    line.isNotBlank() -> result.dropLast(1) + listOf(Group(result.last().answers + listOf(line.trim())))
                    else -> result + listOf(Group())
                }
            }
}

data class Group(val answers: List<String> = emptyList()) {

    val uniques: String
        get() = answers
            .fold(if (answers.isNotEmpty()) answers.first() else "")
            { acc, s -> acc.toCharArray().intersect(s.toList()).joinToString("") }
}
