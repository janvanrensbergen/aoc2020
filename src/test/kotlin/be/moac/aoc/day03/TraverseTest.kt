package be.moac.aoc.day03

import be.moac.aoc.day03.Traverse
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TraverseTest {

    @Test
    internal fun `that part one will count trees on the map traversing from left top to bottom`() {

        //Given:
        val input = """ 
            |..##.......
            |#...#...#..
            |.#....#..#.
            |..#.#...#.#
            |.#...##..#.
            |..#.##.....
            |.#.#.#....#
            |.#........#
            |#.##...#...
            |#...##....#
            |.#..#...#.#""".trimMargin()

        //When:
        val result = Traverse.partOne(input)

        //Then:
        assertThat(result).isEqualTo(7)
    }

    @Test
    internal fun `that part two is correct`() {

        //Given:
        val input = """ 
            |..##.......
            |#...#...#..
            |.#....#..#.
            |..#.#...#.#
            |.#...##..#.
            |..#.##.....
            |.#.#.#....#
            |.#........#
            |#.##...#...
            |#...##....#
            |.#..#...#.#""".trimMargin()

        //When:
        val result = Traverse.partTwo(input)

        //Then:
        assertThat(result).isEqualTo(336)

    }
}
