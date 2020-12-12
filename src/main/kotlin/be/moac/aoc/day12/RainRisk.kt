package be.moac.aoc.day12

import be.moac.aoc.readResource
import be.moac.aoc.timed
import kotlin.math.abs

fun main() {
    val input = "/day12_input.txt".readResource()
    repeat((0..10).count()) {
        RainRisk.partOne(input)
        RainRisk.partTwo(input)
    }
    timed { println("Part one: ${RainRisk.partOne(input)}") }
    timed { println("Part two: ${RainRisk.partTwo(input)}") }
}


object RainRisk {

    fun partOne(input: String): Int {
        val result = mutableMapOf(
            "N" to 0,
            "E" to 0,
            "S" to 0,
            "W" to 0
        )

        var direction = "E"

        fun execute(instruction: String, value:Int) {
            when(instruction) {
                "L" -> direction = direction.left(value/90)
                "R" -> direction = direction.right(value/90)
                "F" -> execute(direction, value)
                else -> result.move(instruction, value)
            }
        }

        input.parse().forEach { execute(it.first, it.second) }
        return abs(result["E"]!!) + abs(result["N"]!!)
    }

    fun partTwo(input: String): Int = 0

}

private fun MutableMap<String,Int>.move(direction: String, value:Int) {
    when(direction) {
        "N" -> this["N"] = this["N"]?.plus(value) ?: value
        "E" -> this["E"] = this["E"]?.plus(value) ?: value
        "S" -> this["N"] = this["N"]?.minus(value) ?: value
        "W" -> this["E"] = this["E"]?.minus(value) ?: value
    }
}

private fun String.left(turns: Int): String =
    if(turns == 0) this
    else when(this) {
        "N" -> "W"
        "E" -> "N"
        "S" -> "E"
        "W" -> "S"
        else -> this
    }.left(turns - 1)

private fun String.right(turns: Int): String =
    if(turns == 0) this
    else when(this) {
        "N" -> "E"
        "E" -> "S"
        "S" -> "W"
        "W" -> "N"
        else -> this
    }.right(turns - 1)


private fun String.parse() =
    this.lines()
        .filter { it.isNotBlank()}
        .map {
            val (a, b) = regex.find(it)!!.destructured
            a to b.toInt()
        }
        


private val regex = "^(\\D)(\\d+)\$".toRegex()




