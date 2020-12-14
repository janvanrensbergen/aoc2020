package be.moac.aoc.day14

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DockingTest {

    @Test
    internal fun `that part one will output the sum of all memory after bitmask is applied`() {

        //Given:
        val input = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent()

        //When:
        val result = Docking.partOne(input)

        //Then:
        assertThat(result).isEqualTo(165)
    }

    @Test
    internal fun `that part two will output the sum of all memory`() {

        //Given:
        val input = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent()

        //When:
        val result = Docking.partTwo(input)

        //Then:
        assertThat(result).isEqualTo(208)
    }
}
