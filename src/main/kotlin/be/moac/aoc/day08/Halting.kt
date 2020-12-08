package be.moac.aoc.day08

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

    fun partOne(input: String) = input.parse().calculateAcc()


    fun partTwo(input: String): Int {
        val instructions = input.parse()
        var indexOfCorrupted = -1

        loop@ for (index in instructions.indices) {
            val pair = instructions[index]
            if(pair.first != "acc"){
                val maybe = instructions.toMutableList()
                maybe[index] =
                    when (pair.first) {
                        "nop" -> "jmp" to pair.second
                        "jmp" -> "nop" to pair.second
                        else -> pair
                    }
                if(!maybe.isInfinite()){
                    indexOfCorrupted = index
                    break@loop
                }
            }
        }

        val fixed = instructions.toMutableList()
        val pair = fixed[indexOfCorrupted]
        fixed[indexOfCorrupted] =
            when (pair.first) {
                "nop" -> "jmp" to pair.second
                "jmp" -> "nop" to pair.second
                else -> pair
            }

        return fixed.calculateAcc()
    }

    private fun List<Pair<String, Int>>.calculateAcc() : Int {
        var accumulator = 0
        var position = 0
        val visited: MutableList<Int> = mutableListOf()


        while (!visited.contains(position) && position < this.size) {
            visited.add(position)
            val (instruction, amount) = this[position]
            when(instruction) {
                "nop"  -> position += 1
                "acc"  -> {
                    accumulator += amount
                    position +=1
                }
                "jmp"  -> {position += amount}
            }
        }

        return accumulator
    }
    private fun List<Pair<String, Int>>.isInfinite() : Boolean {
        var position = 0
        val visited: MutableList<Int> = mutableListOf()

        while(position < this.size){
            position += when(this[position].first) {
                "jmp"  -> this[position].second
                else -> 1
            }
            if(visited.contains(position)) {
                return true
            } else {
                visited.add(position)
            }
        }
        return false
    }
}

fun String.parse() =
    this.lines()
        .filter { it.isNotBlank() }
        .map { it.split("\\s".toRegex()) }
        .map { it.first() to it.last().toInt() }


