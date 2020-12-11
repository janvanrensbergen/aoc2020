package be.moac.aoc.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatingTest {

    @Test
    fun `that part one will count all adjacent occupied seats`() {

        //Given:
        val input = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL   
        """.trimIndent()

        //When:
        val result = Seating.partOne(input)

        //Then:
        assertThat(result).isEqualTo(37)

    }

    @Test
    fun `that part two will count all occupied seats in line of sight`() {

        //Given:
        val input = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL   
        """.trimIndent()

        //When:
        val result = Seating.partTwo(input)

        //Then:
        assertThat(result).isEqualTo(26)

    }

    @Test
    fun `that count occupied seats in line of sight is correct`() {

        //Given:
        val input:Spots = """
            .......#.
            ...#.....
            .#.......
            .........
            ..#L....#
            ....#....
            .........
            #........
            ...#.....
        """.trimIndent().parse()

        //When:
        val result = input.countOccupiedSeatsInLineOfSight(4 to 3)

        //Then:
        assertThat(result).isEqualTo(8)

    }

    @Test
    fun `that count occupied seats in line of sight is correct other example`() {

        //Given:
        val input:Spots = """
            .##.##.
            #.#.#.#
            ##...##
            ...L...
            ##...##
            #.#.#.#
            .##.##.
        """.trimIndent().parse()

        //When:
        val result = input.countOccupiedSeatsInLineOfSight(3 to 3)

        //Then:
        assertThat(result).isEqualTo(0)

    }
}
