package be.moac.aoc.day15

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class RambunctiousTest {

    @Test
    internal fun `that part one will find what the 2020 turn number is`() {

        //Given:
        val input = "0,3,6"

        //When:
        val result = Rambunctious.partOne(input)

        //Then:
        Assertions.assertThat(result).isEqualTo(436)

    }

    @Test
    internal fun `that part two will find what the 30000000th turn number is`() {

        //Given:
        val input = "0,3,6"

        //When:
        val result = Rambunctious.partTwo(input)

        //Then:
        Assertions.assertThat(result).isEqualTo(175594)

    }
}
