package com.dynamic.program.medium.sunset_views

import kotlin.math.max

fun sunsetViews(buildings: List<Int>, direction: String): List<Int> {
    // Write your code here.
    val sunSetViewBuildings = mutableListOf<Int>()
    val startIdx = if (direction == "WEST") 0 else buildings.size -1
    val step = if (direction == "WEST") 1 else  -1

    var currentMaxRunningHeight = 0
    var idx = startIdx
    while (idx >= 0 && idx < buildings.size ) {
        if ( buildings[idx] > currentMaxRunningHeight) {
            sunSetViewBuildings.add(idx)
        }

        currentMaxRunningHeight = max(currentMaxRunningHeight, buildings[idx])
        idx += step
    }
    if (direction == "EAST") {
        sunSetViewBuildings.reverse()
    }
    return sunSetViewBuildings
}

fun sunsetViews_Stack_Approch(buildings: List<Int>, direction: String): List<Int> {
    // Write your code here.
    val sunSetViewBuildings = mutableListOf<Int>()
    val startIdx = if (direction == "EAST") 0 else buildings.size -1
    val step = if (direction == "EAST") 1 else  -1

    var idx = startIdx
    while (idx >= 0 && idx < buildings.size ) {
        val buildingHeight = buildings[idx]

        while ( sunSetViewBuildings.isNotEmpty() && buildings[sunSetViewBuildings.last()] <= buildingHeight) {
            sunSetViewBuildings.removeAt(sunSetViewBuildings.size -1)
        }
        sunSetViewBuildings.add(idx)
        idx += step
    }
    if (direction == "WEST") {
        sunSetViewBuildings.reverse()
    }
    return sunSetViewBuildings
}

fun main() {
    println(sunsetViews(listOf(3,5,4,4,3,1,3,2), "EAST"))
    println(sunsetViews_Stack_Approch(listOf(3,5,4,4,3,1,3,2), "EAST"))
}