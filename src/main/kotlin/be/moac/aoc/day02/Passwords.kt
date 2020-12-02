package be.moac.aoc.day02

import be.moac.aoc.readLines

fun main() {
    val input: List<String> = "/day02_input.txt".readLines()
    val result = Passwords.partOne(input)
    println(result)
}

object Passwords {

    fun partOne(input: List<String>): Int {

        return input.asSequence()
            .filter {
                val (lower, upper, letter, pass) = "^(\\d+)-(\\d+)\\s(\\D):\\s(\\D+)".toRegex().find(it)!!.destructured
                (lower.toInt() .. upper.toInt()).contains(pass.count { l -> l == letter[0] })
            }
            .count()
    }
}
