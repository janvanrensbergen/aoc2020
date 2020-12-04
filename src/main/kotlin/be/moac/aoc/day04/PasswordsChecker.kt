package be.moac.aoc.day04

import be.moac.aoc.day02.Passwords
import be.moac.aoc.day03.Traverse
import be.moac.aoc.readLines
import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input: String = "/day04_input.txt".readResource()
    (0..10).forEach{
        PasswordsChecker.partOne(input)
    }
    timed { println("Part one: ${PasswordsChecker.partOne(input)}") }
//    timed { println("Part two: ${Passwords.partTwo(input)}") }
}

object PasswordsChecker {

    fun partOne(input: String): Int =
        input.parse()
            .filter { password -> password.isValid() }
            .count()


    private fun String.parse(): List<String> =
        this.lineSequence()
            .fold(listOf("")) { result, line ->
                when {
                    line.isNotBlank() -> result.dropLast(1) + listOf("${result.last()} $line")
                    else -> result + listOf("")
                }
            }

    private fun String.isValid(): Boolean =
        this.contains("ecl")
            .and(this.contains("pid"))
            .and(this.contains("eyr"))
            .and(this.contains("hcl"))
            .and(this.contains("byr"))
            .and(this.contains("iyr"))
            .and(this.contains("hgt"))

}
