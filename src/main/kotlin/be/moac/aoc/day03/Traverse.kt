package be.moac.aoc.day03

import be.moac.aoc.readResource
import be.moac.aoc.timed

fun main() {
    val input: String = "/day03_input.txt".readResource()

    (0..10).forEach{
        Traverse.partOne(input)
        Traverse.partTwo(input)
    }

    timed { println("Part one: ${Traverse.partOne(input)}") }
    timed { println("Part two: ${Traverse.partTwo(input)}") }
}

object Traverse {

    fun partOne(input: String): Long = input.parseMap().calculateTrees()

    fun partTwo(input: String): Long {
        val map= input.parseMap()
        return map.calculateTrees(right = 1, down = 1) *
        map.calculateTrees(right = 3, down = 1)*
        map.calculateTrees(right = 5, down = 1)*
        map.calculateTrees(right = 7, down = 1)*
        map.calculateTrees(right = 1, down = 2)
    }

    private fun Array<CharArray>.calculateTrees(right: Int = 3, down: Int = 1) =
        (down until this.size step down)
            .asSequence()
            .map { this[it][it*right/down % this[it].size] }
            .filter { c -> c == '#' }
            .count().toLong()


    private fun String.parseMap(): Array<CharArray> =
        this.lines()
            .asSequence()
            .filter {line -> line.isNotBlank()}
            .map { line -> line.toCharArray()}
            .toList()
            .toTypedArray()

}
