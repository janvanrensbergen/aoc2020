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

        val parsedInput = input.parse()

        val rules = parsedInput.rules
        val nearbyTickets = parsedInput.nearbyTickets



        return nearbyTickets
            .flatMap {
                it.numbers
                    .filterNot { number -> rules.isValid(number) }
            }
            .sum()
    }
    fun partTwo(input: String): Int = 0
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

private fun String.parseRules():List<Rule> =
    this.lines()
        .filter { it.isNotBlank() }
        .map {
            val(name, a1, b1, a2, b2) = ruleRegex.find(it)!!.destructured
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

private fun Rules.isValid(number:Int) =
    this.any { it.firstRange.contains(number) || it.secondRange.contains(number)}

class Input(val rules: List<Rule>, val yourTicket: Ticket, val nearbyTickets: List<Ticket>)
data class Rule(val name: String, val firstRange: IntRange, val secondRange: IntRange)

data class Ticket(val numbers: IntArray)
