package com.dynamic.program.binary.search

/**
 * Minimum Time to Complete Trips
 */
class MinimumTimeToCompleteTrips {
    fun minimumTime(time: IntArray, totalTrips: Int): Long {
        var left = 0L
        var right = Long.MAX_VALUE/time.size
        while(left < right) {
            val mid = left + (right - left) / 2L
            var ttl = 0L
            for( i in time.indices){
                ttl += mid/time[i]
                if (ttl == totalTrips.toLong()){
                    break
                }
            }
            if(ttl < totalTrips) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}
fun main() {
    println(MinimumTimeToCompleteTrips().minimumTime(intArrayOf(5,10,10), 9))
}