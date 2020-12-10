package be.moac.aoc.day10

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AdapterTest {

    @Test
    internal fun `that part one will find differences of 1 and 3 jolts`() {

        //Given:
        val input = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()

        //When:
        val result = Adapter.partOne(input)

        //Then:
        assertThat(result).isEqualTo(7*5)
    }

    @Test
    internal fun `that part one will find differences of 1 and 3 jolts other example`() {

        //Given:
        val input = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()

        //When:
        val result = Adapter.partOne(input)

        //Then:
        assertThat(result).isEqualTo(220)
    }
    
}
