package be.moac.aoc.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ShuttleSearchTest {

    @Test
    internal fun `that part one will find the first bus that leaves to the airport`() {

        //Given:
        val input = """
            939
            7,13,x,x,59,x,31,19    
        """.trimIndent()

        //When:
        val result = ShuttleSearch.partOne(input)

        //Then:
        assertThat(result).isEqualTo(295)
    }
}
