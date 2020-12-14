package be.moac.aoc.day14

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
        val memory = mutableMapOf<Long, Long>()
        var currentMask: Mask = Mask("")

        input
            .parse()
            .forEach {
                when (it) {
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

    fun partTwo(input: String): Long {
        val memory = mutableMapOf<Long, Long>()
        var currentMask: Mask = Mask("")

        input
            .parse()
            .forEach {
                when (it) {
                    is Mask -> currentMask = it
                    is Instruction.Mem -> {
                        it.applyAddressMask(currentMask)
                            .forEach { result ->
                                memory[result.address] = result.value
                            }
                    }
                    Instruction.NoOp -> TODO()
                }
            }

        return memory.values.sum()
    }
}

private val memRegex = "^mem\\[(\\d+)\\]\\s=\\s(\\d+)".toRegex()

private fun String.parse() =
    this.lines()
        .filter { it.isNotBlank() }
        .map {
            when {
                it.startsWith("mask") -> Mask(it.split("=")[1].trim())
                it.startsWith("mem") -> {
                    val (address, value) = memRegex.find(it)!!.destructured
                    Instruction.Mem(address.toLong(), value.toLong())
                }
                else -> Instruction.NoOp
            }
        }

sealed class Instruction {
    data class Mask(val value: String) : Instruction()


    data class Mem(val address: Long, val value: Long) : Instruction() {
        fun applyMask(mask: Mask): Mem {
            val bits = value.toBitString().toCharArray()
            mask.value
                .toCharArray()
                .withIndex()
                .filter { it.value.toLowerCase() != 'x' }
                .forEach { bits[it.index] = it.value }

            return Mem(address, java.lang.Long.parseLong(bits.joinToString(""), 2))
        }

        fun applyAddressMask(mask: Mask): List<Mem> {
            val bits = address.toBitString().toCharArray()
            return mask.value
                .toCharArray()
                .foldIndexed(mutableListOf("")) { index: Int, acc, c ->
                    when (c) {
                        'X' -> acc.map { "${it}0" }.plus(acc.map { "${it}1" }).toMutableList()
                        else ->
                            when (c) {
                                '0' -> acc.map { "$it${bits[index]}" }.toMutableList()
                                else -> acc.map { "$it$c" }.toMutableList()
                            }
                    }
                }.map { a -> java.lang.Long.parseLong(a, 2) }
                .map { Mem(it, value) }
        }
    }

    object NoOp : Instruction()
}

fun Long.toBitString(): String =
    java.lang.Long.toBinaryString(this).padStart(36, '0')
