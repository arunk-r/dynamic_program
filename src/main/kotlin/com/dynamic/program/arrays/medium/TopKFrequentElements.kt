package com.dynamic.program.arrays.medium

class TopKFrequentElements {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = hashMapOf<Int, Int>()
        for(x in nums) {
            map[x] = map.getOrDefault(x, 0) + 1
        }
        val count = Array(nums.size+1){mutableListOf<Int>()}
        map.forEach{ (k, v) ->
            count[v].add(k)
        }

        val result = mutableListOf<Int>()
        for(i in k until count.size) {
            result.addAll(count[i])
        }

        return result.toIntArray()
    }
}

fun main() {
    println(TopKFrequentElements().topKFrequent(intArrayOf(1), 1))
}
