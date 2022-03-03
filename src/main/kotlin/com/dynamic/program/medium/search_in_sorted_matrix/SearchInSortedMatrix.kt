package com.dynamic.program.medium.search_in_sorted_matrix

fun searchInSortedMatrix(matrix: List<List<Int>>, target: Int): Pair<Int, Int> {
    // Write your code here.
    var row = 0
    var col = matrix[0].size - 1
    while (row < matrix[0].size && col >= 0) {
        if (matrix[row][col] > target) {
            col -= 1
        } else if (matrix[row][col] < target) {
            row += 1
        } else {
            return Pair(row, col)
        }
    }
    return Pair(-1, -1)
}

fun main() {
    val matrix = listOf(
        listOf(1, 4, 7, 12, 15, 1000),
        listOf(2, 5, 19, 31, 32, 1001),
        listOf(3, 8, 24, 33, 35, 1002),
        listOf(40, 41, 42, 44, 45, 1003),
        listOf(99, 100, 103, 106, 128, 1004))
    println(searchInSortedMatrix(matrix, 44))
}