package be.moac.aoc.day02

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PasswordsTest {

    @Test
    internal fun `that part one will count how many passwords are valid`() {

        //Given:
        val input = listOf("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")

        //When:
        val result = Passwords.partOne(input)

        //Then:
        Assertions.assertThat(result).isEqualTo(2)

    }
}
