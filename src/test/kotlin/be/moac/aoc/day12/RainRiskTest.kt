package be.moac.aoc.day12

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RainRiskTest {

    @Test
    internal fun `that part one navigates to the given input`() {

        //Given:
        val input = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent()

        //When:
        val result = RainRisk.partOne(input)

        //Then:
        assertThat(result).isEqualTo(25)

    }

    @Test
    internal fun `that part two navigates to the given input`() {

        //Given:
        val input = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent()

        //When:
        val result = RainRisk.partTwo(input)

        //Then:
        assertThat(result).isEqualTo(286)

    }
}
