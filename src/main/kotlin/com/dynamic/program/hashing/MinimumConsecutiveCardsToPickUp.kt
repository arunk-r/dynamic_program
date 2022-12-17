package com.dynamic.program.hashing

/**
 * Minimum Consecutive Cards to Pick Up
 *
 * Given an integer array cards, find the length of the shortest subarray that contains at least one duplicate. If the array has no duplicates, return -1.
 *
 *  For example, given cards = [1, 2, 6, 2, 1], we would map
 *  1: [0, 4], 0th and 4th index - to reach another card 1 we need to jump 4 cards
 *  2: [1, 3], 1st and 3rd index - to reach another card 2 we need to jump 2 cards - hence answer
 *  and 6: [2] 2nd index
 *
 *  time complexity is O(n)
 */

fun minConsecutiveCards(cards: IntArray): Int {
    var ans = Int.MAX_VALUE
    val map = hashMapOf<Int, MutableList<Int>>()
    var idx = 0
    cards.forEach { card ->
        if (map.containsKey(card)) {
            map[card]?.add(idx)
            val x:Int = map[card]?.get(0) ?: 0
            ans = Math.min(ans, (idx - x))
        } else {
            map[card] = mutableListOf(idx)
        }
        idx++
    }
    return if (ans == Int.MAX_VALUE) -1 else ans
}

fun main() {
    println(minConsecutiveCards(intArrayOf(1, 2, 6, 2, 1)))
}


