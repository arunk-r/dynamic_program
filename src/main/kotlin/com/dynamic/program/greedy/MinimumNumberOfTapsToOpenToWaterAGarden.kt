package com.dynamic.program.greedy

/**
 * Minimum Number of Taps to Open to Water a Garden
 */
class MinimumNumberOfTapsToOpenToWaterAGarden {
    fun minTaps(n: Int, ranges: IntArray): Int {
        val maxReach = IntArray(n+1)
        for((i, v) in ranges.withIndex()) {
            if(v != 0) {
                // Calculate the leftmost position the tap can reach
                val start = maxOf(0, i - v)
                // Calculate the rightmost position the tap can reach
                val end = minOf(n, i + v)
                maxReach[start] = maxOf(maxReach[start], end);
            }
        }

        // Number of taps used
        var taps = 0
        // Current rightmost position reached
        var currEnd = 0
        // Next rightmost position that can be reached
        var nextEnd = 0
        // Iterate through the garden
        for (i in 0..n) {
            // Current position cannot be reached
            if (i > nextEnd) {
                return -1
            }
            // Increment taps when moving to a new tap
            if (i > currEnd) {
                taps++
                // Move to the rightmost position that can be reached
                currEnd = nextEnd
            }

            // Update the next rightmost position that can be reached
            nextEnd = maxOf(nextEnd, maxReach[i])
        }
        // Return the minimum number of taps used
        return taps
    }

    fun minTaps1(n: Int, ranges: IntArray): Int {
        // to hold the tap range values
        val set = mutableListOf<IntArray>()
        // inset the data as per problem defination.
        //the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
        for (i in ranges.indices) {
            if(ranges[i] != 0) {
                var left = i - ranges[i]
                if (left < 0)
                    left = 0
                set.add(intArrayOf(left, i + ranges[i]))
            }
        }
        // another error condition if all range values are zero
        if (set.isEmpty()) return -1

        // sort the set
        set.sortWith(kotlin.Comparator { x, y -> if (x[0] == y[0]) y[1] - x[1] else x[0] - y[0] })

        // get the first value to start the algorithm
        var cnt = 1
        var end = set[0][1]
        var idx = 1
        // loop to find next best value (Return the minimum number of taps that should be open to water the whole garden)
        while(idx < set.size && end < n) {
            var nEnd = end
            while (idx < set.size && set[idx][0] <= end) {
                nEnd = Math.max(set[idx][1], nEnd)
                idx++
            }
            if (end == nEnd) return -1
            end = nEnd
            cnt++
        }
        // end is less than n then final top did not reach till end
        if (end < n) return -1
        return cnt
    }
}

fun main() {
    println(MinimumNumberOfTapsToOpenToWaterAGarden().minTaps(5, intArrayOf(3,0,1,1,0,1)))
    //println(MinimumNumberOfTapsToOpenToWaterAGarden().minTaps(8, intArrayOf(4,0,0,0,4,0,0,0,4)))
    //println(MinimumNumberOfTapsToOpenToWaterAGarden().minTaps(7, intArrayOf(1,2,1,0,2,1,0,1)))
    //println(MinimumNumberOfTapsToOpenToWaterAGarden().minTaps(5, intArrayOf(3,4,1,1,0,0)))
    //println(MinimumNumberOfTapsToOpenToWaterAGarden().minTaps(5, intArrayOf(0,0,0,0,0,0)))
}
