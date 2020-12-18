package be.moac.aoc.day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class OperationOrderTest {

    @ParameterizedTest
    @CsvSource(
        "1 + 2 * 3 + 4 * 5 + 6, 71",
        "1 + (2 * 3) + (4 * (5 + 6)), 51",
        "2 * 3 + (4 * 5), 26",
        "5 + (8 * 3 + 9 + 3 * 4 * 3), 437",
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)), 12240",
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2, 13632"
    )
    internal fun `part one`(input: String, expected: Long) {

        //Given:

        //When:
        val result = OperationOrder.partOne(input)

        //Then:
        assertThat(result).isEqualTo(expected)

    }

    @ParameterizedTest
    @CsvSource(
        "1 + 2 * 3 + 4 * 5 + 6, 231",
        "1 + (2 * 3) + (4 * (5 + 6)), 51",
        "2 * 3 + (4 * 5), 46",
        "5 + (8 * 3 + 9 + 3 * 4 * 3), 1445",
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)), 669060",
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2, 23340"
    )
    internal fun `part two`(input: String, expected: Long) {

        //Given:

        //When:
        val result = OperationOrder.partTwo(input)

        //Then:
        assertThat(result).isEqualTo(expected)

    }

    @Test
    internal fun `part one sum`() {

        //Given:
        val input = """
            1 + 2 * 3 + 4 * 5 + 6
            1 + (2 * 3) + (4 * (5 + 6))
            2 * 3 + (4 * 5)
            5 + (8 * 3 + 9 + 3 * 4 * 3)
            5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))
            ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2    
        """.trimIndent()

        //When:
        val result = OperationOrder.partOne(input)

        //Then:
        assertThat(result).isEqualTo(71+51+26+437+12240+13632)

    }
    @Test
    internal fun `part one other`() {

        //Given:
        val input = """
            1 + (2 * 3) + (4 * (5 + 6))
        """.trimIndent()

        //When:
        val result = OperationOrder.partOne(input)

        //Then:
        assertThat(result).isEqualTo(51)

    }

    @Test
    internal fun `group parentheses`() {

        val result = "((5+9)+10)+20".groupParentheses()

        assertThat(result).isEqualTo("(5+9)+10" to "+20")

    }

    @Test
    internal fun `that calculate is correct`() {

        val result = "4 * 11".calculate()
        assertThat(result).isEqualTo(44)

    }
}
