package com.dynamic.program.design.medium.arrays

import java.util.TreeMap
import java.util.TreeSet

/**
 * https://leetcode.com/problems/design-a-leaderboard/description/
 *
 * 1244. Design A Leaderboard
 * Medium
 * 703
 * 91
 * company
 * Bloomberg
 * company
 * Google
 * company
 * Amazon
 * Design a Leaderboard class, which has 3 functions:
 *
 * addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 * top(K): Return the score sum of the top K players.
 * reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
 * Initially, the leaderboard is empty.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 * Output:
 * [null,null,null,null,null,null,73,null,null,null,141]
 *
 * Explanation:
 * Leaderboard leaderboard = new Leaderboard ();
 * leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 * leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 * leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 * leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 * leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.top(1);           // returns 73;
 * leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 * leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 * leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 *
 *
 * Constraints:
 *
 * 1 <= playerId, K <= 10000
 * It's guaranteed that K is less than or equal to the current number of players.
 * 1 <= score <= 100
 * There will be at most 1000 function calls.
 */
class Leaderboard {
    data class Data(val playerId: Int, var score: Int = 0)
    private val map = hashMapOf<Int, Data>()
    private val treeMap = TreeMap<Int, Int>{ x, y -> y - x}

    fun addScore(playerId: Int, score: Int) {
        var d = map[playerId]
        if(d == null) {
            d = Data(playerId, score)
            map[playerId] = d
        } else {
            treeMap[d.score] = treeMap[d.score]!! - 1
            if(treeMap[d.score] == 0) {
                treeMap.remove(d.score)
            }
            d.score += score
        }
        treeMap[score] = treeMap.getOrDefault(d.score, 0 ) + 1
    }

    fun top(K: Int): Int {
        var idx = 0
        var sum = 0
        for((d, v) in treeMap) {
            for (i in 1 .. v) {
                sum += d
                idx++
                if (idx == K) {
                    return sum
                }
            }
        }
        return sum
    }

    fun reset(playerId: Int) {
        val d = map[playerId]
        if(d != null) {
            treeMap[d.score] = treeMap[d.score]!! - 1
            if(treeMap[d.score] == 0) {
                treeMap.remove(d.score)
            }
            map.remove(playerId)
        }
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * var obj = Leaderboard()
 * obj.addScore(playerId,score)
 * var param_2 = obj.top(K)
 * obj.reset(playerId)
 */

fun main() {
    val leaderboard = Leaderboard()
    leaderboard.addScore(1,73)   // leaderboard = [[1,73]];
    leaderboard.addScore(2,56)   // leaderboard = [[1,73],[2,56]];
    leaderboard.addScore(3,39)   // leaderboard = [[1,73],[2,56],[3,39]];
    leaderboard.addScore(4,51)   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
    leaderboard.addScore(5,4)    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
    println( leaderboard.top(1))           // returns 73;
    leaderboard.reset(1)         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
    leaderboard.reset(2)         // leaderboard = [[3,39],[4,51],[5,4]];
    leaderboard.addScore(2,51)   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
    println(leaderboard.top(3))           // returns 141 = 51 + 51 + 39;
}
