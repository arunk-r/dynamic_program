package com.dynamic.program.binary.search

import java.util.PriorityQueue

/**
 * Number of Flowers in Full Bloom
 */
class NumberOfFlowersInFullBloom {
    fun fullBloomFlowers(flowers: Array<IntArray>, persons: IntArray): IntArray {
        val queue = PriorityQueue<Triple<Int, Int, Int>>{x,y -> if(x.first == y.first) x.second - y.second else x.first - y.first}
        /**
         * Initiation - continues even based calculation
         *
         * Here I am using Triple to hold data
         * first -> start/ed of bloom time and person visit time
         * second -> 0 -> identification of begin of bloom
         *           1 -> person visiting
         *           2 -> end of bloom (flower died)
         * third -> index of flower
         *
         * then add both flowers and persons into q
         *
         **/
        val result = IntArray(persons.size){0}
        flowers.forEach { f ->
            queue.add(Triple(f[0], 0, -1))
            queue.add(Triple(f[1], 2, -1))
        }
        for (i in persons.indices){
            queue.add(Triple(persons[i], 1, i))
        }
        var bloom = 0
        while (queue.isNotEmpty()) {
            val (_, identification, index) = queue.remove()
            if (identification == 0){
                bloom++
            } else if (identification == 1) {
                result[index] = bloom
            } else {
                bloom--
            }
        }
        return result
    }
}

fun main() {

}