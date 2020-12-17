package be.moac.aoc.day17

import be.moac.aoc.readResource
import be.moac.aoc.timed


@Suppress("DuplicatedCode")
object ConwayCubesPartTwo {

    fun partTwo(input: String): Int {
        fun Space4d.countActiveNeighbours(position: Point4d): Int =
            listOf(-1,0,1).flatMap { x ->
                listOf(-1,0,1).flatMap { y ->
                    listOf(-1,0,1).flatMap { z ->
                        listOf(-1,0,1).map { w ->
                            if ((x != 0 || y != 0 || z != 0 || w != 0)) {
                                if(this[Point4d(position.x + x, position.y + y, position.z + z, position.w + w)] == true)
                                1 else 0
                            } else {
                                0
                            }
                        }
                    }
                }
            }.sum()

        fun Space4d.min(selector:Point4d.() -> Int) = this.keys.map { it.selector() }.min()?:0
        fun Space4d.max(selector:Point4d.() -> Int) = this.keys.map { it.selector() }.max()?:0

        tailrec fun cycle(
            current: Space4d,
            cycle: Int,
            xMinMax: Pair<Int, Int>,
            yMinMax: Pair<Int, Int>,
            zMinMax: Pair<Int, Int>,
            wMinMax: Pair<Int, Int>
        ): Space4d {
            println("========================= $cycle ==========================")

            return if (cycle == 7) current
            else {
                val s = initialize4dSpace()

                loop(
                    xMinMax = xMinMax,
                    yMinMax = yMinMax,
                    zMinMax = zMinMax,
                    wMinMax = wMinMax
                ) { point ->
                    val activeCube = current[point] ?: false
                    val activeNeighbours = current.countActiveNeighbours(point)


                    when {
                        activeCube && activeNeighbours in (2..3) -> s[point] = true
                        !activeCube && 3 == activeNeighbours -> s[point] = true
                    }
                }

                println("========================= ${s.values.filter { it }.count()} ==========================")

                cycle(
                    current = s,
                    cycle = cycle + 1,
                    xMinMax = -20 to 20,
                    yMinMax = -20 to 20,
                    zMinMax = -20 to 20,
                    wMinMax = -20 to 20
//                    xMinMax = current.min { x } to current.max { x },
//                    yMinMax = current.min { y } to current.max { y },
//                    zMinMax = current.min { z } to current.max { z },
//                    wMinMax = current.min { w } to current.max { w }
                )
            }
        }

        val result = cycle(
            current = input.parse4d(),
            cycle = 1,
            xMinMax = 0 to 10,
            yMinMax = 0 to 10,
            zMinMax = 0 to 0,
            wMinMax = 0 to 0

        )
        return result.values.filter { it }.count()
    }
}



private fun String.parse4d(): Space4d {

    val space: Space4d = initialize4dSpace()

    this.lines()
        .filter { it.isNotBlank() }
        .withIndex()
        .forEach { line ->
            val y = line.index
            line.value
                .toCharArray()
                .withIndex()
                .forEach {
                    val x = it.index
                    space[Point4d(x, y, 0, 0)] = it.value == '#'
                }
        }
    return space
}

private typealias Space4d = MutableMap<Point4d, Boolean>

private fun initialize4dSpace(): Space4d = mutableMapOf()

data class Point4d(val x: Int, val y: Int, val z: Int, val w: Int) {

    operator fun plus(other: Point4d) =
        Point4d((x + other.x), (y + other.y), (z + other.z), (w + other.w))

}


private fun <R> loop(
    xMinMax: Pair<Int, Int>,
    yMinMax: Pair<Int, Int>,
    zMinMax: Pair<Int, Int>,
    wMinMax: Pair<Int, Int>,
     block: (Point4d) -> R) =
    (xMinMax.first -1 .. xMinMax.second +1).forEach { x ->
        (yMinMax.first -1 .. yMinMax.second +1).forEach { y ->
            (zMinMax.first -1 .. zMinMax.second +1).forEach { z ->
                (wMinMax.first -1 .. wMinMax.second +1).forEach { w ->
                    block(Point4d(x, y, z, w))
                }
            }
        }
    }

