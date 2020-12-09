package be.moac.aoc.day09

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EncodingErrorTest {


    @Test
    internal fun `that part one will find the first number that not applies to the rules`() {

        //Given:
        val input = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576   
        """.trimIndent()

        //When:
        val result = EncodingError.partOne(input, 5)

        //Then:
        assertThat(result).isEqualTo(127)

    }
}
