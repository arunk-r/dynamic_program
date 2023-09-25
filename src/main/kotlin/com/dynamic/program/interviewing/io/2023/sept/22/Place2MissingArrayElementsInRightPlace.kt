package com.dynamic.program.interviewing.io.`2023`.sept.`22`

class Place2MissingArrayElementsInRightPlace {
    fun sortArray(arr: IntArray) {
        for(pos in 0 until arr.size-1) {
            if(arr[pos] > arr[pos+1]) {
                var innerPos = pos
                while(innerPos >= 0 && arr[innerPos] > arr[innerPos+1]) {
                    swap(arr, innerPos, innerPos+1)
                    innerPos--
                }
            }
        }
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val tmp = arr[i]
        arr[i] = arr[j]
        arr[j] = tmp
    }
}

fun main() {
    val input = intArrayOf(1,4,3,2,5,9,8,7)
    val obj = Place2MissingArrayElementsInRightPlace()
    obj.sortArray(input)
    println(input.toList())
}
