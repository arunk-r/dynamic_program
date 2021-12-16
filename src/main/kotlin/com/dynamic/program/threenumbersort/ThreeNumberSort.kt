package com.dynamic.program.threenumbersort

fun threeNumberSort(array: MutableList<Int>, order: List<Int>): List<Int> {
    // Write your code here.
    val valueCnt = mutableListOf(0, 0, 0)
    for (element in array) {
        val idx = order.indexOf(element)
        if (idx > -1) {
            valueCnt[idx] += 1
        }
    }

    for (i in 0 until 3) {
        val value = order[i]
        val cnt = valueCnt[i]

        val numberElementBefore = valueCnt.subList(0, i).sum()
        for (n in 0 until cnt) {
            val currentIdx = numberElementBefore + n
            array[currentIdx] = value
        }
    }
    return array
}

fun threeNumberSort1(array: MutableList<Int>, order: List<Int>): List<Int> {
    val firstValue = order[0]
    val secondValue = order[1]

    //keep track of where to swap
    var firstIdx = 0
    var secondIdx = 0
    var thirdIdx = array.size - 1

    while (secondIdx <= thirdIdx) {
        val value = array[secondIdx]

        if (value == firstValue) {
            swap(firstIdx, secondIdx, array)
            firstIdx += 1
            secondIdx += 1
        } else if (value == secondValue) {
            secondIdx += 1
        } else {
            swap(secondIdx, thirdIdx, array)
            thirdIdx -= 1
        }
    }
    return array
}

fun swap(i: Int, j: Int, array: MutableList<Int>) {
    val tmp = array[i]
    array[i] = array[j]
    array[j] = tmp
}

fun main() {
    println(threeNumberSort(mutableListOf(1, 0, 0, -1, -1, 0, 1, 1), listOf(0,1,-1)))
    println(threeNumberSort1(mutableListOf(1, 0, 0, -1, -1, 0, 1, 1), listOf(0,1,-1)))
}