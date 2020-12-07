package be.moac.aoc.day07

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HaversacksTest {

    @Test
    internal fun `that part one counts how many coloured bags can hold a shiny gold bag`() {

        //Given:
        val input = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.    
        """.trimIndent()

        //When:
        val result = Haversacks.partOne(input)

        //Then:
        assertThat(result).isEqualTo(4)
    }

    @Test
    internal fun `that part two counts how many coloured bags needed for a shiny gold bag`() {

        //Given:
        val input = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.    
        """.trimIndent()

        //When:
        val result = Haversacks.partTwo(input)

        //Then:
        assertThat(result).isEqualTo(32)
    }

    @Test
    internal fun `that part two counts how many coloured bags needed for a shiny gold bag other`() {

        //Given:
        val input = """
            shiny gold bags contain 2 dark red bags.
            dark red bags contain 2 dark orange bags.
            dark orange bags contain 2 dark yellow bags.
            dark yellow bags contain 2 dark green bags.
            dark green bags contain 2 dark blue bags.
            dark blue bags contain 2 dark violet bags.
            dark violet bags contain no other bags.    
        """.trimIndent()

        //When:
        val result = Haversacks.partTwo(input)

        //Then:
        assertThat(result).isEqualTo(126)
    }
}
