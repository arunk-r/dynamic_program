package com.dynamic.program.minimumPassesOfMatrix

import java.util.*

fun minimumPassesOfMatrix(matrix: MutableList<MutableList<Int>>): Int {
    // Write your code here.
    val passes = convertNavigates(matrix)
    return if (!containsNegative(matrix)) passes -1 else  - 1
}

fun convertNavigates(matrix: MutableList<MutableList<Int>>) : Int {
    var nextQueue = getAllPositivePositions(matrix)
    var passes = 0
    while (nextQueue.isNotEmpty()) {
        val currentQueue = nextQueue
        nextQueue = mutableListOf()
        while (currentQueue.isNotEmpty()) {
            val pair = currentQueue.removeAt(0)
            val adjacentNodes = getAdjacentNodes(matrix, pair.first, pair.second)

            for (node in adjacentNodes) {
                val value = matrix[node.first][node.second]
                if (value < 0) {
                    matrix[node.first][node.second] = value * -1
                    nextQueue.add(node)
                }
            }
            println(matrix)
        }
        println()
        passes += 1
    }
    return passes
}

fun getAdjacentNodes(matrix: MutableList<MutableList<Int>>, row: Int, col: Int): MutableList<Pair<Int, Int>>{
    val list = mutableListOf<Pair<Int, Int>>()
    if (row > 0) list.add(Pair(row -1, col))
    if (row < matrix.size -1) list.add(Pair(row +1, col))
    if (col > 0) list.add(Pair(row, col -1))
    if (col < matrix[row].size -1) list.add(Pair(row, col +1))
    return  list
}

fun getAllPositivePositions(matrix: MutableList<MutableList<Int>>): MutableList<Pair<Int, Int>> {
    val queue = mutableListOf<Pair<Int, Int>>()
    for (r in matrix.indices) {
        for (c in matrix[r].indices) {
            if (matrix[r][c] > 0) {
                queue.add(Pair(r, c))
            }
        }
    }
    return queue
}

fun containsNegative(matrix: MutableList<MutableList<Int>>) : Boolean {
    for (r in matrix.indices) {
        for (c in matrix[r].indices) {
            if (matrix[r][c] < 0) {
                return true
            }
        }
    }
    return false
}

fun main() {
    println(minimumPassesOfMatrix(mutableListOf(mutableListOf(0, -1, -3, 2, 0), mutableListOf(1, -2, -5, -1, -3), mutableListOf(3, 0, 0, -4, -1))))
    println(minimumPassesOfMatrix(mutableListOf(mutableListOf(0, -1, -3, -2, 0), mutableListOf(-1, -2, -5, -1, -3), mutableListOf(-3, 0, 0, -4, -1))))
}