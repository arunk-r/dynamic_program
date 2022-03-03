package com.dynamic.program.hard.zigzag_traverse

/**
 * Write a function that takes in an n x m two-dimensional array (that can be
 * square-shaped when n == m) and returns a one-dimensional array of all the
 * array's elements in zigzag order.
 *
 * Zigzag order starts at the top left corner of the two-dimensional array, goes
 * down by one element, and proceeds in a zigzag pattern all the way to the
 * bottom right corner.
 *
 * Sample Input
 * = [
 * [1,  3,  4, 10],
 * [2,  5,  9, 11],
 * [6,  8, 12, 15],
 * [7, 13, 14, 16],
 * ]
 * Sample Output
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
 *
 */

fun zigzagTraverse(array: List<List<Int>>): List<Int> {
    // Write your code here.

    val height = array.size - 1
    val width = array[0].size - 1

    var row = 0
    var col = 0
    val result = mutableListOf<Int>()
    var goingDown = true
    //check bounds and loop
    while (!(row < 0 || row > height || col < 0 || col > width)) {
        result.add(array[row][col])
        if (goingDown) {
            if (col == 0 || row == height) {
                goingDown = false
                if (row == height) {
                    col ++
                } else {
                    row ++
                }
            } else {
                row ++
                col --
            }
        } else {
            if (row == 0 || col == width) {
                goingDown = true
                if (col == width) {
                    row ++
                } else {
                    col ++
                }
            } else {
                row --
                col ++
            }
        }
    }
    return result
}

fun main() {
    println(
        zigzagTraverse(
            listOf(
                listOf(1, 3, 4, 10),
                listOf(2, 5, 9, 11),
                listOf(6, 8, 12, 15),
                listOf(7, 13, 14, 16),
            )
        )
    )
}