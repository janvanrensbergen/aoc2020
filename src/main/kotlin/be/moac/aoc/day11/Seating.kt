@file:Suppress("TAILREC_WITH_DEFAULTS")

package be.moac.aoc.day11

import be.moac.aoc.readResource
import be.moac.aoc.timed
import java.lang.IllegalArgumentException


fun main() {
    val input = "/day11_input.txt".readResource().parse()
    repeat((0..10).count()) {
        Seating.partOne(input)
        Seating.partTwo(input)
    }
    timed { println("Part one: ${Seating.partOne(input)}") }
    timed { println("Part two: ${Seating.partTwo(input)}") }
}


object Seating {

    fun partOne(input: String): Int = partOne(input.parse())
    fun partOne(input: Spots): Int = input.haveASeat(
        emptyPredicate = {position -> this.countAdjacentOccupiedSeats(position) == 0},
        occupiedPredicate = {position -> this.countAdjacentOccupiedSeats(position) >= 4}
    ).countOccupied()

    fun partTwo(input: String) = partTwo(input.parse())
    fun partTwo(input: Spots) = input.haveASeat(
        emptyPredicate = {position -> this.countOccupiedSeatsInLineOfSight(position) == 0},
        occupiedPredicate = {position -> this.countOccupiedSeatsInLineOfSight(position) >= 5}
    ).countOccupied()
}

private tailrec fun Spots.haveASeat(
    changed: Boolean = true,
    emptyPredicate: Spots.(Point) -> Boolean,
    occupiedPredicate: Spots.(Point) -> Boolean
): Spots {
    if (!changed) return this
    var isChanged = false
    val result = this.mapIndexed { r, row ->
        row.mapIndexed { c, spot ->
            when (spot) {
                Spot.Empty -> {
                    when {
                        emptyPredicate(r to c) -> {
                            isChanged = true
                            Spot.Occupied
                        }
                        else -> spot
                    }
                }
                Spot.Occupied -> {
                    when {
                        occupiedPredicate(r to c) -> {
                            isChanged = true
                            Spot.Empty
                        }
                        else -> spot
                    }
                }
                else -> spot
            }
        }.toTypedArray()
    }.toTypedArray()

//    result.print()
//    println("==========================================")


    return result.haveASeat(isChanged, emptyPredicate, occupiedPredicate)
}

fun Spots.countOccupiedSeatsInLineOfSight(position: Point): Int {
    val maxX = this.size
    val maxY = this[0].size

    tailrec infix fun Point.move(to: Point): Point {
        val moved: Point = this.first + to.first to  this.second + to.second
        return when {
            moved.first < 0 || moved.second < 0 -> -1 to -1
            moved.first >= maxX || moved.second >= maxY -> -1 to -1
            this@countOccupiedSeatsInLineOfSight[moved.first][moved.second] != Spot.Floor -> moved
            else -> moved move to
        }
    }


    fun Point.adjacent(): Array<Point> {
        return points.map { this move it }
            .filter { it.first >= 0 && it.second >= 0 }
            .toTypedArray()
    }

    return position.adjacent()
        .map { this[it.first][it.second] }
        .filter { it == Spot.Occupied }
        .count()
}

private fun Spots.countAdjacentOccupiedSeats(position: Point): Int {
    fun Point.adjacent(): Array<Point> =
        points.map { this.first - it.first to this.second - it.second }
            .filter { it.first >= 0 && it.second >= 0 }
            .filter { it.first < this@countAdjacentOccupiedSeats.size && it.second < this@countAdjacentOccupiedSeats[0].size }
            .toTypedArray()

    return position.adjacent()
        .map { this[it.first][it.second] }
        .filter { it == Spot.Occupied }
        .count()
}

fun String.parse(): Spots =
    this.lines()
        .filter { it.isNotBlank() }
        .map { it.trim() }
        .map { line -> line.parseLine() }
        .toTypedArray()

private fun String.parseLine(): Array<Spot> =
    this.toCharArray()
        .map {
            when (it) {
                '.' -> Spot.Floor
                'L' -> Spot.Empty
                '#' -> Spot.Occupied
                else -> throw IllegalArgumentException()
            }
        }
        .toTypedArray()

sealed class Spot {
    object Floor : Spot() {
        override fun toString(): String = "."
    }

    object Empty : Spot() {
        override fun toString(): String = "L"
    }

    object Occupied : Spot() {
        override fun toString(): String = "#"
    }
}

typealias Point = Pair<Int, Int>
typealias Spots = Array<Array<Spot>>


fun Spots.copy(): Spots = Array(size) { get(it).clone() }
fun Spots.print() = this.forEach { println(it.joinToString("")) }
fun Spots.countOccupied() = this
    .flatMap { it.asList() }
    .filter { it == Spot.Occupied }
    .count()

//@formatter:off
private val points =
    arrayOf(
        -1 to -1, -1 to 0, -1 to +1,
         0 to -1,           0 to +1,
        +1 to -1, +1 to 0, +1 to +1
    )
//@formatter:on

