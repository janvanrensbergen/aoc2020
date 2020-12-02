package be.moac.aoc.day02

import be.moac.aoc.readLines

fun main() {
    val input: List<String> = "/day02_input.txt".readLines()
    println("Part one: ${Passwords.partOne(input)}")
    println("Part two: ${Passwords.partTwo(input)}")
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

    fun partTwo(input: List<String>): Int {
        return input.asSequence()
            .filter {
                val (positionA, positionB, letter, pass) = "^(\\d+)-(\\d+)\\s(\\D):\\s(\\D+)".toRegex().find(it)!!.destructured

                val a = pass[positionA.toInt()-1]
                val b = pass[positionB.toInt()-1]

                listOf(a,b).count { x -> x == letter[0] } == 1
            }
            .count()
    }
}
