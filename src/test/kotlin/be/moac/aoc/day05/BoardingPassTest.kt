package be.moac.aoc.day05

import be.moac.aoc.day05.BoardingPass.findMissing
import org.assertj.core.api.Assertions.assertThat
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

        //When:
        val result = BoardingPass.partOne(input)

        //Then:
        assertThat(result).isEqualTo(820)
    }

    @Test
    internal fun `that missing number can be found`() {

        //Given:
        val input = listOf(5,6,7,8,9,11,12)

        //When:
        val result = input.asSequence().findMissing()

        //Then:
        assertThat(result).isEqualTo(10)

    }
}
