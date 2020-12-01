package be.moac.aoc

import java.io.File

fun String.readResource(): String = object{}.javaClass.getResource(this).readText()
fun <R> String.readLines(map:(String) -> R = { i -> i as R }): List<R> = File(object{}.javaClass.getResource(this).file)
    .readLines()
    .map(map)
