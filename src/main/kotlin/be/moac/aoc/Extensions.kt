package be.moac.aoc

import java.io.File
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

fun String.readResource(): String = object{}.javaClass.getResource(this).readText()
fun <R> String.readLines(map:(String) -> R = { i -> i as R }): List<R> = File(object{}.javaClass.getResource(this).file)
    .readLines()
    .map(map)


fun timed(block: () -> Unit) {
    val time = measureTimeMillis(block)
    println("Time: $time ms")
}
