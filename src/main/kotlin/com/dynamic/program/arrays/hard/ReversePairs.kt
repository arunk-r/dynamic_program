package com.dynamic.program.arrays.hard

class ReversePairs {
    fun reversePairs(nums: IntArray): Int {
        // try BIT approch to store the count
        // binary search to find the element
        val bit = IntArray(nums.size*2)
        val sortNums = nums.distinct().sorted()

        var count = 0
        for(n in nums.distinct()) {
            count += query(bit, searchElement(sortNums, n*2L+1))
            update(bit, searchElement(sortNums, n*1L))
        }
        return count
    }

    private fun update(bit: IntArray, num: Int) {
        var idx = num
        while (idx > 0) {
            bit[idx] += 1
            idx -= idx.and(-idx)
        }
    }

    private fun query(bit: IntArray, i: Int): Int {
        var sum = 0
        var idx = i
        while (idx < bit.size) {
            sum += bit[idx]
            idx += idx.and(-idx)
        }
        return sum
    }

    private fun searchElement(nums: List<Int>, target: Long): Int {
        var l = 0
        var r = nums.size - 1
        while(l <= r) {
            val mid = l + (r - l)/2
            if(nums[mid] >= target) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return l + 1
    }
}

fun main() {
    println(ReversePairs().reversePairs(intArrayOf(2147483647,2147483647,2147483647,2147483647,2147483647,2147483647)))
    //println(ReversePairs().reversePairs(intArrayOf(-5, -5)))
    //println(ReversePairs().reversePairs(intArrayOf(1,3,2,3,1)))
    //println(ReversePairs().reversePairs(intArrayOf(2,4,3,5,1)))
}
