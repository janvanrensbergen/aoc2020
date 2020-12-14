package be.moac.aoc.day14

import org.assertj.core.api.Assertions
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
        Assertions.assertThat(result).isEqualTo(165)

        "100100000110100000110001010110101101"
    }
}
