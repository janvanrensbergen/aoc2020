package be.moac.aoc.day06

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CustomsTest  {

    @Test
    internal fun `that part one will count how many answered yes`() {

        //Given:
        val input = """
            abc
            
            a
            b
            c
            
            ab
            ac
            
            a
            a
            a
            a
            
            b
        """.trimIndent()
        //When:
        val result = Customs.partOne(input)

        //Then:
        assertThat(result).isEqualTo(11)
    }

    @Test
    internal fun `that part two will count how many everybody from a group answered yes`() {

        //Given:
        val input = """
            abc
            
            a
            b
            c
            
            ab
            ac
            
            a
            a
            a
            a
            
            b
        """.trimIndent()
        //When:
        val result = Customs.partTwo(input)

        //Then:
        assertThat(result).isEqualTo(6)
    }
}
