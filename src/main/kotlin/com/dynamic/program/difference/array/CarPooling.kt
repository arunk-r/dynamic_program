package com.dynamic.program.difference.array

/**
 * 1094. Car Pooling
Medium
There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.



Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true


Constraints:

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 105
 */
class CarPooling {
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        var tripLength = 0
        trips.forEach{ c ->
            tripLength = maxOf(tripLength, c[2])
        }

        val dp = IntArray(tripLength+1)

        trips.forEach{ c ->
            val v = c[0]
            val start = c[1]
            val end = c[2]

            dp[start] += v
            dp[end] -= v
        }

        var currentCapacity = 0
        dp.forEach{ v ->
            currentCapacity += v
            if (currentCapacity > capacity) return false
        }

        return true
    }
}
