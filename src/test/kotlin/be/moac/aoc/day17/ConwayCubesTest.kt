package be.moac.aoc.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ConwayCubesTest {

    @Test
    internal fun `that part one will count all active cubes`() {

        //Given:
        val input = """
            .#.
            ..#
            ###
        """.trimIndent()

        //When:
        val result = ConwayCubes.partOne(input)

        //Then:
        assertThat(result).isEqualTo(112)

    }

    @Test
    internal fun `that part two will count all active cubes in 4 dimensions`() {

        //Given:
        val input = """
            .#.
            ..#
            ###
        """.trimIndent()

        //When:
        val result = ConwayCubesPartTwo.partTwo(input)

        //Then:
        assertThat(result).isEqualTo(848)

    }

}



