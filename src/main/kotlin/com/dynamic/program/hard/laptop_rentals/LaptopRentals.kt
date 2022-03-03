package com.dynamic.program.hard.laptop_rentals

import com.dynamic.program.medium.min_heap.MinHeap

/**
 * You're given a list of time intervals during which students at a school need a
 * laptop. These time intervals are represented by pairs of integers
 * [start, end], where 0 &lt;= start &lt; end. However,
 * start and end don't represent real times; therefore,
 * they may be greater than 24.
 *
 * No two students can use a laptop at the same time, but immediately after a
 * student is done using a laptop, another student can use that same laptop. For
 * example, if one student rents a laptop during the time interval
 * [0, 2], another student can rent the same laptop during any time
 * interval starting with 2.
 *
 * Write a function that returns the minimum number of laptops that the school
 * needs to rent such that all students will always have access to a laptop when
 * they need one.
 *
 * Sample Input
 * times = [[0, 2],[1, 4],[4, 6],[0, 4],[7, 8],[9, 11],[3, 10]]

 * Sample Output
 * 3
 *
 * Sol: You can use minHeap to compare start tim with end time or comparing start time vs end time in separate array
 */
fun laptopRentals(times: List<List<Int>>): Int {
    // Write your code here.
    if (times.isEmpty()) return 0
    var laptops = 0
    val startTimeValues = times.map { it[0] }.toList().sorted()
    val endTimeValues = times.map { it[1] }.toList().sorted()
    println(startTimeValues)
    println(endTimeValues)
    var j = 0
    for (i in startTimeValues.indices) {
        if (startTimeValues[i] >= endTimeValues[j]) {
            laptops++
            j++
        }
    }
    return laptops
}

fun laptopRentalsWithMinHeap(times: List<List<Int>>): Int {
    if (times.isEmpty()) return 0
    val sortedList = times.sortedWith(kotlin.Comparator { o1, o2 -> o1[0].compareTo(o2[0]) })
    println(sortedList)
    val minHeap = MinHeap(mutableListOf())
    minHeap.insert(sortedList[0][1])
    for (i in 1 until sortedList.size) {
        //println("${minHeap.peek()}, ${sortedList[i][1]}")
        if (minHeap.peek() <= sortedList[i][0]) {
            minHeap.remove()
        }
        minHeap.insert(sortedList[i][1])
    }
    return minHeap.size()
}

fun main() {
    println(
        laptopRentals(
            listOf(
                listOf(0, 2),
                listOf(1, 4),
                listOf(4, 6),
                listOf(0, 4),
                listOf(1, 3),
                listOf(7, 8),
                listOf(9, 11),
                listOf(3, 10)
            )
        )
    )
    println(
        laptopRentalsWithMinHeap(
            listOf(
                listOf(0, 2),
                listOf(1, 4),
                listOf(4, 6),
                listOf(0, 4),
                listOf(1, 3),
                listOf(7, 8),
                listOf(9, 11),
                listOf(3, 10)
            )
        )
    )
    /*println(
        laptopRentals(
            listOf(
                listOf(10, 20),
                listOf(0, 5),
                listOf(5, 10),
                listOf(10, 15)
            )
        )
    )*/
}