package be.moac.aoc.day15

import be.moac.aoc.timed


fun main() {
    val input = "17,1,3,16,19,0"
//    repeat((0..10).count()) {
//        Rambunctious.partOne(input)
//        Rambunctious.partTwo(input)
//    }
    timed { println("Part one: ${Rambunctious.partOne(input)}") }
    timed { println("Part two: ${Rambunctious.partTwo(input)}") }
}

object Rambunctious {

    fun partOne(input: String): Long {
        val turns = input.initial()
        val turn = turns.size.toLong()

        return play(maxTurns = 2020L, turn = turn, turns = turns, lastTurn = turns.entries.last().toPair())
    }
    fun partTwo(input: String): Long {
        val turns = input.initial()
        val turn = turns.size.toLong()

        return play(maxTurns = 30000000L,turn = turn, turns = turns, lastTurn = turns.entries.last().toPair())
    }

    tailrec fun play(maxTurns:Long = 2020L, turn: Long, turns: MutableMap<Long, Long>, lastTurn: Pair<Long, Long>): Long {
        if(turn == maxTurns) return lastTurn.first

        val previous = turns[lastTurn.first]
        turns[lastTurn.first] = lastTurn.second

        return when (previous) {
            null -> play(
                maxTurns = maxTurns,
                turn = turn + 1,
                turns = turns,
                lastTurn = 0L to turn + 1L
            )
            else -> {
                play(
                    maxTurns = maxTurns,
                    turn = turn + 1,
                    turns = turns,
                    lastTurn = lastTurn.second - previous to turn + 1
                )
            }
        }
    }

}

private fun String.initial() =
      this.split(",")
          .map { it.toLong() }
          .foldIndexed(mutableMapOf<Long, Long>()) { index, acc, s ->
              acc[s] = index + 1L
              acc
          }
