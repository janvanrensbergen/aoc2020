package be.moac.aoc.day12

import be.moac.aoc.readResource
import be.moac.aoc.timed
import kotlin.math.abs

typealias ShipPosition = MutableMap<String, Int>

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
        val result = mutableMapOf("N" to 0, "E" to 0)
        var direction = "E"

        fun execute(instruction: String, value: Int) {
            when (instruction) {
                "L" -> direction = direction.left(value / 90)
                "R" -> direction = direction.right(value / 90)
                "F" -> execute(direction, value)
                else -> result.move(instruction, value)
            }
        }

        input.parse().forEach { execute(it.first, it.second) }
        return abs(result["E"]!!) + abs(result["N"]!!)
    }

    fun partTwo(input: String): Int {
        val shipPosition = mutableMapOf("N" to 0, "E" to 0)
        var waypoint = Waypoint("N" to 1, "E" to 10)

        fun execute(instruction: String, value: Int) {
            when (instruction) {
                "L" -> waypoint = waypoint.turnLeft(value)
                "R" -> waypoint = waypoint.turnRight(value)
                "F" -> shipPosition.move(waypoint, value)
                else -> waypoint = waypoint.move(instruction, value)
            }
        }

        input.parse().forEach { execute(it.first, it.second) }
        return abs(shipPosition["E"]!!) + abs(shipPosition["N"]!!)
    }

}

private fun ShipPosition.move(waypoint: Waypoint, value: Int) {
    this.move(waypoint.x.first, waypoint.x.second * value )
    this.move(waypoint.y.first, waypoint.y.second * value )
}

private fun ShipPosition.move(direction: String, value: Int) {
    when (direction) {
        "N" -> this["N"] = this["N"]?.plus(value) ?: value
        "E" -> this["E"] = this["E"]?.plus(value) ?: value
        "S" -> this["N"] = this["N"]?.minus(value) ?: value
        "W" -> this["E"] = this["E"]?.minus(value) ?: value
    }
}

private class Waypoint(val x: Pair<String, Int>, val y: Pair<String, Int>) {

    fun turnLeft(degrees: Int): Waypoint =
        Waypoint(
            x = x.first.left(degrees/90) to x.second,
            y = y.first.left(degrees/90) to y.second
        )

    fun turnRight(degrees: Int): Waypoint =
        Waypoint(
            x = x.first.right(degrees/90) to x.second,
            y = y.first.right(degrees/90) to y.second
        )

    fun move(direction: String, value: Int): Waypoint =
        Waypoint(
            x = x.move(direction, value),
            y = y.move(direction, value)
        )

    private fun Pair<String, Int>.move(direction: String, value: Int): Pair<String, Int> =
        when  {
            first == "N" && direction == "N" -> "N" to second + value
            first == "N" && direction == "S" -> "N" to second - value

            first == "S" && direction == "S" -> "S" to second + value
            first == "S" && direction == "N" -> "S" to second - value

            first == "E" && direction == "E" -> "E" to second + value
            first == "E" && direction == "W" -> "E" to second - value

            first == "W" && direction == "W" -> "W" to second + value
            first == "W" && direction == "E" -> "W" to second - value
            else -> this
        }
}

private fun String.left(turns: Int): String =
    if (turns == 0) this
    else when (this) {
        "N" -> "W"
        "E" -> "N"
        "S" -> "E"
        "W" -> "S"
        else -> this
    }.left(turns - 1)

private fun String.right(turns: Int): String =
    if (turns == 0) this
    else when (this) {
        "N" -> "E"
        "E" -> "S"
        "S" -> "W"
        "W" -> "N"
        else -> this
    }.right(turns - 1)


private fun String.parse() =
    this.lines()
        .filter { it.isNotBlank() }
        .map {
            val (a, b) = regex.find(it)!!.destructured
            a to b.toInt()
        }


private val regex = "^(\\D)(\\d+)\$".toRegex()





