@file:Suppress("ArrayInDataClass")

package be.moac.aoc.day16

import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input = "/day16_input.txt".readResource()
    repeat((0..10).count()) {
        TicketTranslation.partOne(input)
        TicketTranslation.partTwo(input)
    }
    timed { println("Part one: ${TicketTranslation.partOne(input)}") }
    timed { println("Part two: ${TicketTranslation.partTwo(input)}") }
}

object TicketTranslation {
    fun partOne(input: String): Int {
        val (rules, _, nearbyTickets) = input.parse()

        return nearbyTickets
            .flatMap { it.numbers.filterNot { number -> rules.isValid(number) } }
            .sum()
    }

    fun partTwo(input: String): Long {
        val (rules, myTicket, nearbyTickets) = input.parse()

        tailrec fun go(indices: List<Int>, rules: Rules, result: Map<String, Int>): Map<String, Int> {
            if (indices.isEmpty()) return result

            val match = rules.map { rule ->
                val valids = indices.map { index ->
                    val valid = nearbyTickets
                        .map { it.numbers[index] }
                        .all { rule.isValid(it) }

                    index to valid
                }.filter { it.second }
                    .map { it.first }

                rule to valids
            }.first { it.second.count() == 1 }

            val map = result .toMutableMap()
            map[match.first.name] = match.second.first()

            val list = indices.toMutableList()
            list.remove(match.second.first())

            val list1 = rules.toMutableList()
            list1.remove(match.first)

            return go(list, list1, map)
        }

        val result = go(rules.indices.toList(), rules, emptyMap())

        return result.entries
            .filter { it.key.startsWith("departure") }
            .map { myTicket.numbers[it.value].toLong() }
            .fold(1L) {acc, i -> acc * i }
    }
}

private fun String.parse(): Input {
    val split = this.split("your ticket:")
    val split1 = split[1].split("nearby tickets:")

    val rules = split[0]
    val yourTicket = split1[0].lines().first { it.isNotBlank() }
    val nearbyTickets = split1[1]

    return Input(
        rules = rules.parseRules(),
        yourTicket = yourTicket.parseTicket(),
        nearbyTickets = nearbyTickets.parseTickets()
    )
}

private val ruleRegex = "^(\\D+):\\s(\\d+)-(\\d+)\\sor\\s(\\d+)-(\\d+)".toRegex()

private fun String.parseRules(): List<Rule> =
    this.lines()
        .filter { it.isNotBlank() }
        .map {
            val (name, a1, b1, a2, b2) = ruleRegex.find(it)!!.destructured
            Rule(name, a1.toInt()..b1.toInt(), a2.toInt()..b2.toInt())
        }

private fun String.parseTickets() =
    this.lines()
        .filter { it.isNotBlank() }
        .map { it.parseTicket() }

private fun String.parseTicket() =
    Ticket(this.split(",")
        .map { it.trim() }
        .map { it.toInt() }
        .toIntArray())

typealias Rules = List<Rule>

private fun Rules.isValid(number: Int) =
    this.any { it.isValid(number) }

class Input(val rules: List<Rule>, val yourTicket: Ticket, val nearbyTickets: List<Ticket>) {
    val validNearbyTickets
        get() =
            nearbyTickets.filter { it.numbers.all { number -> rules.isValid(number) } }


    operator fun component1() = rules
    operator fun component2() = yourTicket
    operator fun component3() = nearbyTickets


}

data class Rule(val name: String, val firstRange: IntRange, val secondRange: IntRange) {
    fun isValid(number: Int) =
        firstRange.contains(number) || secondRange.contains(number)
}

data class Ticket(val numbers: IntArray)
