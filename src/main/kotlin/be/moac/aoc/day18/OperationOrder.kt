package be.moac.aoc.day18

import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input = "/day18_input.txt".readResource()
    repeat((0..2).count()) {
        OperationOrder.partOne(input)
        OperationOrder.partTwo(input)
    }
    timed { println("Part one: ${OperationOrder.partOne(input)}") }
    timed { println("Part two: ${OperationOrder.partTwo(input)}") }
}


object OperationOrder {

    fun partOne(input: String): Long {
        fun String.doIt(): Long {
            return when {
                this.contains("(") -> {
                    val prefix = this.substring(0, this.indexOfFirst { it == '(' })
                    val (first, second) = this.substring(this.indexOfFirst { it == '(' }).groupParentheses()
                    "$prefix${first.doIt()}$second".doIt()
                }
                else -> return calculate()
            }
        }


        return input.lines()
            .asSequence()
            .filter { it.isNotBlank() }
            .map { it.doIt() }
            .toList()
            .sum()
    }

    fun partTwo(input: String): Long {
        fun String.doItAgain(): Long {
            return when {
                this.contains("(") -> {
                    val prefix = this.substring(0, this.indexOfFirst { it == '(' })
                    val (first, second) = this.substring(this.indexOfFirst { it == '(' }).groupParentheses()
                    "$prefix${first.doItAgain()}$second".doItAgain()
                }
                this.contains("*") && this.contains("+") -> {
                    split("*")
                        .map { it.doItAgain() }
                        .joinToString(" * ")
                        .doItAgain()
                }
                else -> {
                    return calculate()
                }
            }
        }


        return input.lines()
            .asSequence()
            .filter { it.isNotBlank() }
            .map { it.doItAgain() }
            .toList()
            .sum()
    }
}

internal fun String.groupParentheses(): Pair<String, String> {

    data class Result(val value: String, val numberOfOpen: Int, val numberOfClosed: Int)

    val result = this.toCharArray()
        .fold(Result("", 0, 0)) { acc, c ->
            if (acc.numberOfOpen == 0 || acc.numberOfOpen > acc.numberOfClosed) {
                when (c) {
                    '(' -> Result("${acc.value}$c", acc.numberOfOpen + 1, acc.numberOfClosed)
                    ')' -> Result("${acc.value}$c", acc.numberOfOpen, acc.numberOfClosed + 1)
                    else -> Result("${acc.value}$c", acc.numberOfOpen, acc.numberOfClosed)
                }
            } else {
                acc
            }
        }
        .value

    return result.substring(1 until result.length - 1) to substring(result.length until length)
}

private val regex = "(\\d+|\\D+)\\s*".toRegex()

fun String.calculate(): Long =
    regex.findAll(this.trim())
        .map { it.value }
        .fold(0L to "+") { acc, c ->
            val second = c.trim().toLongOrNull()
            second?.let { number ->
                when (acc.second.trim()) {
                    "+" -> acc.first + number to acc.second
                    "*" -> acc.first * number to acc.second
                    else -> acc
                }
            } ?: acc.first to c
        }.first



