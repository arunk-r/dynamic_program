package com.dynamic.program

fun validStartingCity(distances: List<Int>, fuel: List<Int>, mpg: Int): Int {
    // Write your code here.
    var milesRemaining = 0
    var indexOfStartingCity = 0
    var milesRemainingAtStatingCity = 0
    for (idx in 1 until distances.size) {
        milesRemaining += fuel[idx - 1] * mpg - distances[idx - 1]
        if (milesRemaining < milesRemainingAtStatingCity) {
            indexOfStartingCity = idx
            milesRemainingAtStatingCity = milesRemaining
        }
    }
    return indexOfStartingCity
}

fun main() {
    println(validStartingCity(listOf(5, 25, 15, 10,15), listOf(1, 2, 1, 0, 3), 10))
}