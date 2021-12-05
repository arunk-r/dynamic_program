package com.dynamic.program.taskAssignment

fun taskAssignment(k: Int, tasks: MutableList<Int>): List<List<Int>> {
    // Write your code here.
    val sortedList = mutableListOf<Int>()
    sortedList.addAll(tasks)
    sortedList.sort()
    val tempList = mutableListOf<Int>()
    var beginIndex = 0
    var endIndex = sortedList.size - 1
    val output = mutableListOf<MutableList<Int>>()
    while (true) {
        if (beginIndex >= endIndex + 1) break
        tempList.add(sortedList[beginIndex])
        tempList.add(sortedList[endIndex])
        beginIndex++
        endIndex--
    }
    while (tempList.isNotEmpty()) {
        var d = tempList.removeAt(0)
        val idx1 = tasks.indexOf(d)
        tasks[idx1] = Int.MIN_VALUE

        d = tempList.removeAt(0)
        val idx2 = tasks.indexOf(d)
        tasks[idx2] = Int.MIN_VALUE

        output.add(mutableListOf(idx1, idx2))
    }
    return output
}


fun main() {
    println(taskAssignment(3, mutableListOf(1, 3, 5, 3, 1, 4)))
}