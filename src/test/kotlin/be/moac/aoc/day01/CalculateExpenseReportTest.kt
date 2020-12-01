package be.moac.aoc.day01

import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CalculateExpenseReportTest {


    @Test
    internal fun `that calculate expense report will find 2 numbers that sums to 2020 and multiply them`() {

        //Given:
        val input = listOf(1721L, 979L, 366L, 299L, 675L, 1456L)

        //When:
        val result = CalculateExpenseReport partOne input

        //Then:
        Assertions.assertThat(result).isEqualTo(514579L)

    }

    @Test
    internal fun `that part two will find 3 numbers that sums to 2020 and multiply them`() {

        //Given:
        val input = listOf(1721L, 979L, 366L, 299L, 675L, 1456L)

        //When:
        val result = CalculateExpenseReport partTwo input

        //Then:
        Assertions.assertThat(result).isEqualTo(241861950L)

    }
}
