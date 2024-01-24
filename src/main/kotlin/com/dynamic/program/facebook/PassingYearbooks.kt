package com.dynamic.program.facebook

class PassingYearbooks {
    fun findSignatureCounts(arr: Array<Int>): IntArray {
        // Write your code here
        val result = IntArray(arr.size)
        for(i in arr.indices) {
            var first = true
            var idx = i
            var len = 0
            while(first || idx != i) {
                len++
                first = false
                idx = arr[idx] - 1
            }
            idx = i
            first = true
            while(first || idx != i) {
                first = false
                result[idx] = len
                idx = arr[idx] - 1
            }
        }
        return result
    }
}

fun main() {
    //println(PassingYearbooks().findSignatureCounts(arrayOf(2,1)).toList())
    println(PassingYearbooks().findSignatureCounts(arrayOf(1,2)).toList())
}
