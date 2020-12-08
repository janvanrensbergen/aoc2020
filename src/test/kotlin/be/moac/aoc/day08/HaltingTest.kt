package be.moac.aoc.day08

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HaltingTest {

    @Test
    internal fun `that part one find infinit loop`() {

        //Given:
        val input = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()

        //When:
        val result = Halting.partOne(input)

        //Then:
        assertThat(result).isEqualTo(5)
    }

    @Test
    internal fun `that part one fixes infinit loop`() {

        //Given:
        val input = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()

        //When:
        val result = Halting.partTwo(input)

        //Then:
        assertThat(result).isEqualTo(8)
    }
}
