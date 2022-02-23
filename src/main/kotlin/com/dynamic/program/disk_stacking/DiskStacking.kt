package com.dynamic.program.disk_stacking

/**
 * You're given a non-empty array of arrays where each subarray holds three
 * integers and represents a disk. These integers denote each disk's width,
 * depth, and height, respectively. Your goal is to stack up the disks and to
 * maximize the total height of the stack. A disk must have a strictly smaller
 * width, depth, and height than any other disk below it.
 *
 * Write a function that returns an array of the disks in the final stack,
 * starting with the top disk and ending with the bottom disk. Note that you
 * can't rotate disks; in other words, the integers in each subarray must
 * represent <span>[width, depth, height] at all times.
 *
 * You can assume that there will only be one stack with the greatest total height.
 *
 * Sample Input
 * disks = [[2, 1, 2], [3, 2, 3], [2, 2, 8], [2, 3, 4], [1, 3, 1], [4, 4, 5]]
 *
 * Sample Output
 * [[2, 1, 2], [3, 2, 3], [4, 4, 5]]
 * //10 (2 + 3 + 5) is the tallest height we can get by
 * //stacking disks following the rules laid out above.
 *
 */

fun diskStacking(disks: List<List<Int>>): List<List<Int>> {
    // Write your code here.
    if (disks.size == 1) return disks
    val heightSortedDisk = disks.sortedWith(kotlin.Comparator{a,b -> a[2].compareTo(b[2])})
    //println(heightSortedDisk)

    val heights = MutableList(disks.size) {-1}
    val sequence = mutableSetOf<List<Int>>()
    for (currentDisk in heightSortedDisk.indices) {
        heights[currentDisk] = heightSortedDisk[currentDisk][2]
        for (otherDisk in 0..currentDisk) {
            if (isValidDisk(heightSortedDisk[currentDisk], heightSortedDisk[otherDisk])) {
                if (heights[currentDisk] <= heightSortedDisk[currentDisk][2] + heights[otherDisk]) {
                    heights[currentDisk] = heightSortedDisk[currentDisk][2] + heights[otherDisk]
                    if (sequence.isEmpty()) {
                        sequence.add(heightSortedDisk[otherDisk])
                    }
                    sequence.add(heightSortedDisk[currentDisk])
                }
            }
        }
    }
    //println(heights)

    return sequence.toList()
}

fun isValidDisk(currentDisk: List<Int>, otherDisk: List<Int>): Boolean {
    return currentDisk[0] > otherDisk[0] &&
            currentDisk[1] > otherDisk[1] &&
            currentDisk[2] > otherDisk[2]
}

fun main() {
    println(diskStacking(listOf(listOf(2, 1, 2), listOf(3, 2, 3), listOf(2, 2, 8), listOf(2, 3, 4), listOf(1, 3, 1), listOf(4, 4, 5))))
}