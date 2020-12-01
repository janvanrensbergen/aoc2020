package be.moac.aoc.day01

import be.moac.aoc.readLines
import be.moac.aoc.readResource

fun main() {
    val input: List<Long> = "/day01_input.txt".readLines { i -> i.toLong()}
//    val result = CalculateExpenseReport partOne input
    val result = CalculateExpenseReport partTwo  input

    println(result)
}

object CalculateExpenseReport {

    infix fun partOne(input: List<Long>): Long {

        input.forEach { first ->
            input.forEach {second ->
                if(first != second && first + second == 2020L) {
                    return first * second
                }
            }
        }

        return 0L
    }

    infix fun partTwo(input: List<Long>): Long {

        input.forEach { first ->
            input.forEach {second ->
                input.forEach { third ->
                    if(first + second + third == 2020L) {
                        return first * second * third
                    }
                }
            }
        }

        return 0L
    }

}
