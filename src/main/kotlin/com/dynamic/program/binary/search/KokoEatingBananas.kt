package com.dynamic.program.binary.search

/**
 * Koko Eating Bananas
 */
class KokoEatingBananas {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        // Initialize the left and right boundaries
        var left = 1
        var right = 1
        for (pile in piles) {
            right = Math.max(right, pile)
        }

        while (left < right) {
            // Get the middle index between left and right boundary indexes.
            // hourSpent stands for the total hour Koko spends.
            val middle = (left + right) / 2
            var hourSpent = 0

            // Iterate over the piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile / middle)
            for (pile in piles) {
                hourSpent += Math.ceil(pile.toDouble() / middle).toInt()
            }

            // Check if middle is a workable speed, and cut the search space by half.
            if (hourSpent <= h) {
                right = middle
            } else {
                left = middle + 1
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the minimum workable eating speed.

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the minimum workable eating speed.
        return right
    }
}

fun main() {
    println(KokoEatingBananas().minEatingSpeed(intArrayOf(30,11,23,4,20), 5))
}