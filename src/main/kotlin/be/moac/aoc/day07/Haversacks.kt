package be.moac.aoc.day07

import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input = "/day07_input.txt".readResource()
    repeat((0..10).count()) {
        Haversacks.partOne(input)
        Haversacks.partTwo(input)
    }
    timed { println("Part one: ${Haversacks.partOne(input)}") }
    timed { println("Part two: ${Haversacks.partTwo(input)}") }
}


object Haversacks {

    private val regex = "^(\\d+)\\s(\\D+)\\sbag".toRegex()

    fun partOne(input: String): Int =
        input.parse()
            .canContain("shiny gold")
            .distinct()
            .count()

    fun partTwo(input: String): Int = 0


    private fun String.parse() =
        this.lines()
            .filter { it.isNotBlank() }
            .fold(mutableMapOf<String, Rules>()) { acc, line ->
                val split = line.split(" bags contain")
                acc[split.first().trim()] =
                    when (val rules = split.last().trim()) {
                        in ".*no\\sother\\sbag.*".toRegex() -> Rules.Empty
                        else -> rules.parseRules()
                    }
                acc
            }.toMap()

    private fun String.parseRules() =
        Rules.Colors(this.split(",")
            .map {
                val (amount, color) = regex.find(it.trim())!!.destructured
                Rule(amount.toInt(), color)
            }
            .toList())

    private operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)
}

private fun Map<String, Rules>.canContain(color: String): List<String> {
    val result = this
        .filter { it.value.canContain(color) }
        .map { it.key }

    return if (result.isEmpty()) {
        result
    } else {
        result + result.flatMap { this.canContain(it) }
    }
}

sealed class Rules {
    abstract val values: List<Rule>
    abstract fun canContain(value: String): Boolean

    data class Colors(override val values: List<Rule>) : Rules() {
        override fun canContain(value: String) =
            values.any { it.color == value }
    }

    object Empty : Rules() {
        override val values: List<Rule>
            get() = emptyList()

        override fun canContain(value: String): Boolean = false
    }
}


data class Rule(val amount: Int, val color: String)

