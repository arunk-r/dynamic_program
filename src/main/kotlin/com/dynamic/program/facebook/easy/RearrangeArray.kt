package com.dynamic.program.facebook.easy

/**
 *
 * Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.

Example:

Input : [1, 0]
Return : [0, 1]
Lets say N = size of the array. Then, following holds true :

All elements in the array are in the range [0, N-1]
N * N does not overflow for a signed integer
 */

fun rearrangeArray(v: Array<Int>): Array<Int> {
    var beginIdx = 0
    var lastIdx = v.size-1
    var t: Int
    while (beginIdx<lastIdx) {
        t = v[beginIdx]
        v[beginIdx] = v[lastIdx]
        v[lastIdx] = t

        beginIdx ++
        lastIdx --
    }
    return v
}

fun main() {
    rearrangeArray(arrayOf(1,2,3,4,5,6,7,8,9)).forEach { print(it) }
    println("-------------")
    rearrangeArray(arrayOf(0,0,0,0,0,0,0,1,1,1,1,1,1)).forEach { print(it) }
}

