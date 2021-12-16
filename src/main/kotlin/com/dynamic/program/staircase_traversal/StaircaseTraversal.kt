package com.dynamic.program.staircase_traversal

fun staircaseTraversal(height: Int, maxSteps: Int): Int {
    // Write your code here.
    var currentNumberOfWays = 0
    val waysToTop = mutableListOf(1)
    for (currentHeight in 1.. height+1) {
        val startOfWindow = currentHeight - maxSteps - 1
        val endOfWindow = currentHeight - 1
        if (startOfWindow >= 0) {
            currentNumberOfWays -= waysToTop[startOfWindow]
        }
        currentNumberOfWays += waysToTop[endOfWindow]
        waysToTop.add(currentNumberOfWays)
    }
    return waysToTop[height]
}

fun staircaseTraversal2(height: Int, maxSteps: Int): Int {
    // Write your code here.
    val waysToTp = MutableList(height + 1) {0}
    waysToTp[0] = 1
    waysToTp[1] = 1
    for (currentHeight in 2 until height + 1) {
        var step = 1
        while (step <= maxSteps && step <= currentHeight) {
            waysToTp[currentHeight] = waysToTp[currentHeight] + waysToTp[currentHeight - step]
            step += 1
        }
    }
    return waysToTp[height]
}

fun main() {
    println(staircaseTraversal(4, 2))
    println(staircaseTraversal2(4, 2))
}