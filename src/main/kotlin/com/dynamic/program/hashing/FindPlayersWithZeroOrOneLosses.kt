package com.dynamic.program.hashing

import java.util.*

/**
 * Find Players With Zero or One Losses
 *
 * Solution
 * You are given an integer array matches where matches[\i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.
 *
 * Return a list answer of size 2 where:
 *
 * answer[0] is a list of all players that have not lost any matches.
 * answer[1] is a list of all players that have lost exactly one match.
 * The values in the two lists should be returned in increasing order.
 *
 * Note:
 * You should only consider the players that have played at least one match.
 * The testcases will be generated such that no two matches will have the same outcome.
 *
 * Example 1:
 *
 * Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
 * Output: [[1,2,10],[4,5,7,8]]
 * Explanation:
 * Players 1, 2, and 10 have not lost any matches.
 * Players 4, 5, 7, and 8 each have lost one match.
 * Players 3, 6, and 9 each have lost two matches.
 * Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
 *
 * Example 2:
 *
 * Input: matches = [[2,3],[1,3],[5,4],[6,4]]
 * Output: [[1,2,5,6],[]]
 * Explanation:
 * Players 1, 2, 5, and 6 have not lost any matches.
 * Players 3 and 4 each have lost two matches.
 * Thus, answer[0] = [1,2,5,6] and answer[1] = [].
 *
 *
 * Constraints:
 *
 * 1 <= matches.length <= 10^5
 * matches[\i].length == 2
 * 1 <= winneri, loseri <= 10^5
 * winneri != loseri
 * All matches[\i] are unique.
 */

/**
 * In this example
 * TreeMap = O(log n)
 * map loop = O(n)
 *
 *  = O ((log n) + n)
 *  = O (n)
 */
fun findWinners(matches: Array<IntArray>): List<List<Int>> {
    val map = TreeMap<Int, Pair<Int, Int>>()
    for (match in matches) {
        val p1 = map[match[0]]
        val p2 = map[match[1]]
        map[match[0]] = Pair(p1?.first?.plus(1) ?: 1, p1?.second ?: 0)
        map[match[1]] = Pair(p2?.first ?: 0, p2?.second?.plus(1) ?: 1)
    }

    val winner = mutableListOf<Int>()
    val losers = mutableListOf<Int>()
    map.forEach { (key, value) ->
        if (value.first != 0 && value.second == 0) {
            winner.add(key)
        }
        if (value.second == 1) {
            losers.add(key)
        }
    }
    return listOf(winner, losers)
}

/**
 * Second approach
 * Look at constraint
 *
 * all we need here is
 *  array of size 10^5
 *  initialize all players with -1 (10^5 players)
 *  if player1 index value == -1 then set value as 0 and consider him as winner
 *  if player2 index value == -1 then set value as 1 and consider him as one time loser
 *  if player2 value already present just increment
 *
 *  Time complexity: O(n + k)
 *  n = matches length
 *  k = constraint
 */

fun findWinners_2(matches: Array<IntArray>): List<List<Int>> {
    val list = MutableList(100000) { -1 }

    matches.forEach { m ->
        val winner = m[0]
        val loser = m[1]
        if (list[winner] == -1) {
            list[winner] = 0
        }
        if (list[loser] == -1) {
            list[loser] = 1
        } else {
            list[loser] = list[loser] + 1
        }
    }

    val winner = mutableListOf<Int>()
    val losers = mutableListOf<Int>()

    for (i in 0 until 100000) {
        if (list[i] == 0) {
            winner.add(i)
        }
        if (list[i] == 1) {
            losers.add(i)
        }
    }

    return listOf(winner, losers)
}

fun main() {
    println(
        findWinners(
            arrayOf(
                intArrayOf(1, 3),
                intArrayOf(2, 3),
                intArrayOf(3, 6),
                intArrayOf(5, 6),
                intArrayOf(5, 7),
                intArrayOf(4, 5),
                intArrayOf(4, 8),
                intArrayOf(4, 9),
                intArrayOf(10, 4),
                intArrayOf(10, 9)
            )
        )
    )

    println(
        findWinners_2(
            arrayOf(
                intArrayOf(1, 3),
                intArrayOf(2, 3),
                intArrayOf(3, 6),
                intArrayOf(5, 6),
                intArrayOf(5, 7),
                intArrayOf(4, 5),
                intArrayOf(4, 8),
                intArrayOf(4, 9),
                intArrayOf(10, 4),
                intArrayOf(10, 9)
            )
        )
    )
}
