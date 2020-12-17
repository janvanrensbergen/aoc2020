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
}



