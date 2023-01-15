package com.dynamic.program.heaps

import java.util.*

/**
 * Top K Frequent Words
 */
class TopKFrequentWords {
    fun topKFrequent(words: Array<String>, k: Int): List<String> {

        val map = hashMapOf<String, Int>()
        words.forEach { w ->
            map.putIfAbsent(w, 1)
            map[w] = map[w]!! + 1
        }

        val minHeap = PriorityQueue<String> { w1, w2 -> if (map[w1] == map[w2]) w2.compareTo(w1) else map[w1]!! - map[w2]!! }

        map.keys.forEach { key ->
            minHeap.add(key)
            if (minHeap.size > k){
                minHeap.remove()
            }
        }
        val l = mutableListOf<String>()
        while (minHeap.size > 0) {
            l.add(minHeap.remove())
        }
        l.reverse()
        return l.toList()
    }
}