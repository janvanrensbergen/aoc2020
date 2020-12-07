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

    fun partOne(input: String): Int =
        input.parse()
            .canContain("shiny gold")
            .distinct()
            .count()

    fun partTwo(input: String): Long =
        input.parse()
            .sum("shiny gold")

}

private fun Map<String, Rules>.sum(color: String): Long {
    val rules = this[color]?.values ?: emptyList()

    return if (rules.isEmpty()) {
        0
    } else {
        rules
            .map { it.amount + (it.amount * sum(it.color)) }
            .sum()
    }
}

private fun Map<String, Rules>.canContain(color: String): List<String> {
    fun find(c: String) = this
        .filter { it.value.canContain(c) }
        .map { it.key }

    tailrec fun recursion(input: List<String>, result: List<String> = emptyList()): List<String> {
        return if(input.isEmpty()) result
            else recursion(input.flatMap { find(it) }, result + input)
    }

    return recursion(find(color))
}

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
            val (amount, color) = "^(\\d+)\\s(\\D+)\\sbag".toRegex().find(it.trim())!!.destructured
            Rule(amount.toInt(), color)
        }
        .toList())

private operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)

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

