package be.moac.aoc.day17

import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input = "/day17_input.txt".readResource()
    repeat((0..2).count()) {
        ConwayCubes.partOne(input)
        ConwayCubes.partTwo(input)
    }
    timed { println("Part one: ${ConwayCubes.partOne(input)}") }
    timed { println("Part two: ${ConwayCubes.partTwo(input)}") }
}

const val size = 50

object ConwayCubes {
    fun partOne(input: String): Int {


        tailrec fun cycle(current: Space, cycle: Int): Space {
            return if (cycle == 6) current
            else {
                val s = initializeSpace()

                loop(size) { x, y, z ->
                    val cube = current[x][y][z]

                    val activeNeighbours = mutations
                        .asSequence()
                        .map { Point(x + it.x, y + it.y, z + it.z) }
                        .filter { point ->
                            point.x in 0 until size &&
                                    point.y in 0 until size &&
                                    point.z in 0 until size
                        }
                        .map { point -> current[point.x][point.y][point.z] }
                        .filter { it }
                        .count()

                    when {
                        cube && activeNeighbours in (2..3) -> s[x][y][z] = true
                        !cube && 3 == activeNeighbours -> s[x][y][z] = true
                        else -> s[x][y][z] = false
                    }

                }

                cycle(s, cycle + 1)
            }
        }

        val result = cycle(input.parse(), 0)
        return result
            .flatMap { it.flatMap { it.toList() } }
            .filter { it }
            .count()
    }

    fun partTwo(input: String): Long = 0L
}

private fun String.parse(): Array<Array<BooleanArray>> {

    val space: Space = initializeSpace()

    this.lines()
        .filter { it.isNotBlank() }
        .withIndex()
        .forEach { line ->
            val y = line.index + (size /2)
            line.value
                .toCharArray()
                .withIndex()
                .forEach {
                    val x = it.index + (size/2)
                    space[x][y][(size /2)] = it.value == '#'
                }
        }

    return space
}

typealias Space = Array<Array<BooleanArray>>

private fun initializeSpace(): Space = Array(size) { Array(size) { BooleanArray(size) { false } } }

private data class Point(val x: Int, val y: Int, val z: Int)

//@formatter:off
private val mutations = listOf(
    Point(-1, +1, -1), Point(0, +1, -1), Point(+1, +1, -1),
    Point(-1,  0, -1), Point(0,  0, -1), Point(+1,  0, -1),
    Point(-1, -1, -1), Point(0, -1, -1), Point(+1, -1, -1),

    Point(-1, +1, 0), Point(0, +1, 0), Point(+1, +1, 0),
    Point(-1,  0, 0),                             Point(+1,  0, 0),
    Point(-1, -1, 0), Point(0, -1, 0), Point(+1, -1, 0),

    Point(-1, +1, +1), Point(0, +1, +1), Point(+1, +1, +1),
    Point(-1,  0, +1), Point(0,  0, +1), Point(+1,  0, +1),
    Point(-1, -1, +1), Point(0, -1, +1), Point(+1, -1, +1)
)
//@formatter:on

private fun <R> loop(size: Int, block: (Int, Int, Int) -> R) =
    (0 until size).forEach { x ->
        (0 until size).forEach { y ->
            (0 until size).forEach { z ->
                block(x, y, z)
            }
        }
    }

