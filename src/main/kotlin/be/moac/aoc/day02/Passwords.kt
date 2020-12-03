package be.moac.aoc.day02

import be.moac.aoc.readLines
import be.moac.aoc.timed

fun main() {
    val input: List<String> = "/day02_input.txt".readLines()
    timed { println("Part one: ${Passwords.partOne(input)}") }
    timed { println("Part one: ${Passwords.partOne(input)}") }
    timed { println("Part two: ${Passwords.partTwo(input)}") }
}

object Passwords {
    val regex = "^(\\d+)-(\\d+)\\s(\\D):\\s(\\D+)".toRegex()

    fun partOne(input: List<String>): Int {
        return input
            .filter {
                val (lower, upper, letter, pass) = regex.find(it)!!.destructured
                pass.filter { l -> l == letter[0]  }.count() in (lower.toInt() .. upper.toInt())
            }
            .count()
    }

    fun partTwo(input: List<String>): Int {
        return input
            .filter {
                val (positionA, positionB, letter, pass) = regex.find(it)!!.destructured

                val a = pass[positionA.toInt()-1]
                val b = pass[positionB.toInt()-1]

                (a == letter[0]).xor(b == letter[0])
            }
            .count()
    }
}
