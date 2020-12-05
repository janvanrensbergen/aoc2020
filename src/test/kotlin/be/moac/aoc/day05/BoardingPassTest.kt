package be.moac.aoc.day05

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BoardingPassTest  {


    @Test
    internal fun `that part one will calculate the highest seat Id on a boarding pass`() {
        
        //Given:
        val input = """
             BFFFBBFRRR
             FFFBBBFRRR
             BBFFBBFRLL
        """.trimIndent()
//        val input = "FBFBBFFRLR"

        //When:
        val result = BoardingPass.partOne(input)

        //Then:
        assertThat(result).isEqualTo(820)
    }
}
