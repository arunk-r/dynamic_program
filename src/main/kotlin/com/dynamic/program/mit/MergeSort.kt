package com.dynamic.program.mit

import java.util.*

fun mergeSort(list: MutableList<Int>, startIdx: Int = 0, endIdx: Int = list.size) {
    if (1 < (endIdx - startIdx)) {
        val midIdx = (startIdx + endIdx + 1) / 2
        mergeSort(list, startIdx, midIdx)
        mergeSort(list, midIdx, endIdx)
        val leftLst = list.subList(startIdx, midIdx).toMutableList()
        val rightLst = list.subList(midIdx, endIdx).toMutableList()
        merge(leftLst, rightLst, list, leftLst.size, rightLst.size, startIdx, endIdx)
    }
}

fun merge(leftLst: MutableList<Int>, rightLst: MutableList<Int>, list: MutableList<Int>, i: Int, j: Int, startIdx: Int, endIdx: Int) {
    if (startIdx < endIdx && endIdx > 0) {
        if (j <= 0 || (i > 0 && leftLst[i-1] > rightLst[j-1])) {
            list[endIdx-1] = leftLst[i-1]
            merge(leftLst, rightLst, list, i-1, j, startIdx, endIdx-1)
        } else {
            list[endIdx-1] = rightLst[j-1]
            merge(leftLst, rightLst, list, i, j-1, startIdx, endIdx-1)
        }
    }
}

fun main(){
    val list = mutableListOf(1,4,2,7,3,9,5,6)
    mergeSort(list)
    println(list)
}