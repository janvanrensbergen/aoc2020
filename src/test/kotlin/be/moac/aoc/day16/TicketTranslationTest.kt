package be.moac.aoc.day16

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TicketTranslationTest {

    @Test
    internal fun `that part one validate nearby tickets by the rules`() {

        //Given:
        val input = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50
            
            your ticket:
            7,1,14
            
            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12    
        """.trimIndent()

        //When:
        val result = TicketTranslation.partOne(input)

        //Then:
        Assertions.assertThat(result).isEqualTo(71)

    }
}
