package be.moac.aoc.day04

import be.moac.aoc.readResource
import be.moac.aoc.timed

typealias Password = String

fun main() {
    val input: String = "/day04_input.txt".readResource()
    (0..10).forEach {
        PasswordsChecker.partOne(input)
        PasswordsChecker.partTwo(input)
    }
    timed { println("Part one: ${PasswordsChecker.partOne(input)}") }
    timed { println("Part two: ${PasswordsChecker.partTwo(input)}") }
}

object PasswordsChecker {

    fun partOne(input: String): Int =
        input.parse()
            .filter { password -> password.hasAllFields() }
            .count()


    fun partTwo(input: String): Int =
        input.parse()
            .asSequence()
            .filter { password -> password.hasAllFields() }
            .filter { pass -> pass.isValid() }
            .count()

    private fun String.parse(): List<String> =
        this.lineSequence()
            .fold(listOf("")) { result, line ->
                when {
                    line.isNotBlank() -> result.dropLast(1) + listOf("${result.last()} $line")
                    else -> result + listOf("")
                }
            }

    private fun String.hasAllFields(): Boolean =
        rules.keys.all { this.contains(it) }


    private fun Password.isValid() =
        this.trim()
            .split("\\s".toRegex())
            .map { it.split(":") }
            .all { rules[it.first()]?.invoke(it.last()) ?: true }

    private val rules = mapOf<String, (String) -> Boolean>(
        "byr" to { s -> s.toIntOrNull()?.let { year -> year in 1920..2002 } ?: false },
        "iyr" to { s -> s.toIntOrNull()?.let { year -> year in 2010..2020 } ?: false },
        "eyr" to { s -> s.toIntOrNull()?.let { year -> year in 2020..2030 } ?: false },
        "hgt" to { s ->
            when {
                s.endsWith("cm") -> s.dropLast(2).toIntOrNull()?.let { height -> height in 150..193 } ?: false
                s.endsWith("in") -> s.dropLast(2).toIntOrNull()?.let { height -> height in 59..76 } ?: false
                else -> false
            }
        },
        "hcl" to { s -> "^#[0-9a-f]{6}$".toRegex().matches(s) },
        "ecl" to { s -> listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(s) },
        "pid" to { s -> s.length == 9 && s.toIntOrNull() != null }
    )
}
