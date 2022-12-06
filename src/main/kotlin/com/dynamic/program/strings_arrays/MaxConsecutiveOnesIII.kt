package com.dynamic.program.strings_arrays

fun longestOnes(nums: IntArray, k: Int): Int {
    /*val leftA = mutableListOf<Int>()
    val newKa = mutableListOf<Int>()
    val rightA = mutableListOf<Int>()*/
    var left = 0
    var right = 0
    var newK = k
    while (right < nums.size) {
        if (nums[right] == 0) {
            newK--
        }
        if (newK < 0) {
            if(nums[left] == 0) {
                newK += 1
            }
            left++
        }
        right++
        /*leftA.add(left)
        newKa.add(newK)
        rightA.add(right)*/
    }
    /*println("L, K, R, V")
    for (i in leftA.indices) {
        println("${leftA[i]} ,${newKa[i]} ,${rightA[i]} ,${nums[i]}")
    }*/
    return right - left
}

fun main() {
    println(longestOnes(intArrayOf(1,1,1,0,0,0,1,1,1,1,0), 2))
    println(longestOnes(intArrayOf(0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1), 3))
}
