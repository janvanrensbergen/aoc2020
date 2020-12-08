package be.moac.aoc.day08

import be.moac.aoc.day07.Haversacks
import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input = "/day08_input.txt".readResource()
    repeat((0..10).count()) {
        Halting.partOne(input)
        Halting.partTwo(input)
    }
    timed { println("Part one: ${Halting.partOne(input)}") }
    timed { println("Part two: ${Halting.partTwo(input)}") }
}



object Halting {

    fun partOne(input: String): Int {
        var accumulator: Int = 0
        var position: Int = 0
        val visited: MutableList<Int> = mutableListOf()

        val instructions = input.parse()

        while (true) {
            val (instruction, amount) = instructions[position]
            println("$position - $instruction - $amount")

            when(instruction) {
                "nop"  -> position += 1
                "acc"  -> {accumulator += amount; position +=1}
                "jmp"  -> {position += amount}
            }

            if(visited.contains(position)) {
                return accumulator
            } else {
                visited.add(position)
            }
        }
    }
    fun partTwo(input: String): Int = 0

}

fun String.parse() =
    this.lines()
        .filter { it.isNotBlank() }
        .map { it.split("\\s".toRegex()) }
        .map { it.first() to it.last().toInt() }


