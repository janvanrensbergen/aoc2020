package be.moac.aoc.day14

import be.moac.aoc.day13.ShuttleSearch
import be.moac.aoc.day14.Instruction.Mask
import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input = "/day14_input.txt".readResource()
    repeat((0..10).count()) {
        Docking.partOne(input)
        Docking.partTwo(input)
    }
    timed { println("Part one: ${Docking.partOne(input)}") }
    timed { println("Part two: ${Docking.partTwo(input)}") }
}


object Docking {

    fun partOne(input: String): Long {

        val memory = mutableMapOf<Int, Long>()
        var currentMask:Mask = Mask("")

        input
            .parse()
            .forEach {
                when(it){
                    is Mask -> currentMask = it
                    is Instruction.Mem -> {
                        val result = it.applyMask(currentMask)
                        memory[result.address] = result.value
                    }
                    Instruction.NoOp -> TODO()
                }
            }

        return memory.values.sum()
    }

    fun partTwo(input: String): Int = 0


}

private fun String.parse() =
    this.lines()
        .filter { it.isNotBlank() }
        .map {
            when {
                it.startsWith("mask") -> Mask(it.split("=")[1].trim())
                it.startsWith("mem") -> {
                    val (address, value) = "^mem\\[(\\d+)\\]\\s=\\s(\\d+)".toRegex().find(it)!!.destructured
                    Instruction.Mem(address.toInt(), value.toLong())
                }
                else -> {
                    println("noop: $it")
                    Instruction.NoOp
                }
            }
        }

sealed class Instruction {
    data class Mask(val value:String): Instruction()


    data class Mem(val address: Int, val value: Long): Instruction() {
        fun applyMask(mask: Mask): Mem {
            val bits = value.toBitString().toCharArray()
//            println(bits.joinToString(""))

            mask.value
                .toCharArray()
                .withIndex()
                .filter { it.value.toLowerCase() != 'x' }
                .forEach { bits[it.index] = it.value }

            val result = bits.joinToString("")
//            println(result)
//            println(Integer.parseInt(result, 2))

            return Mem(address, java.lang.Long.parseLong(result, 2))
        }
    }

    object NoOp: Instruction()
}

fun Long.toBitString(): String =
    java.lang.Long.toBinaryString(this).padStart(36, '0')
