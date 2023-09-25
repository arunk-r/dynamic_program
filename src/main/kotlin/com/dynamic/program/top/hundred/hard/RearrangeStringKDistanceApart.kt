package com.dynamic.program.top.hundred.hard

import java.util.PriorityQueue

/**
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 358. Rearrange String k Distance Apart
 * Hard
 * Topics
 * Companies
 * Given a string s and an integer k, rearrange s such that the same characters are at least distance k from each other. If it is not possible to rearrange the string, return an empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The same letters are at least a distance of 3 from each other.
 * Example 2:
 *
 * Input: s = "aaabc", k = 3
 * Output: ""
 * Explanation: It is not possible to rearrange the string.
 * Example 3:
 *
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least a distance of 2 from each other.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of only lowercase English letters.
 * 0 <= k <= s.length
 */
class RearrangeStringKDistanceApart {
    data class Data(val c: Char, var cnt: Int = 0)

    fun rearrangeString(s: String, k: Int): String {
        val map = HashMap<Char, Int>()
        val q = PriorityQueue<Data> { x, y -> if (x.cnt == y.cnt) x.c - y.c else y.cnt - x.cnt }

        for (c in s) {
            map[c] = map.getOrDefault(c, 0) + 1
        }
        map.forEach { (k, v) ->
            q.add(Data(k, v))
        }

        val buf = StringBuffer()
        val busy = ArrayDeque<Data>()
        while (buf.length != s.length) {
            val index = buf.length
            if (busy.isNotEmpty() && (index - busy.first().cnt) >= k) {
                val d = busy.removeFirst()
                q.add(Data(d.c, map[d.c]!!))
            }
            if (q.isEmpty()) return  ""
            val d = q.remove()
            buf.append(d.c)
            map[d.c] = map[d.c]!! - 1
            if (map[d.c]!! > 0) {
                busy.addLast(Data(d.c, index))
            }
        }
        return buf.toString()
    }
}

fun main() {
    println(RearrangeStringKDistanceApart().rearrangeString("aaadbbcc", 2))
}
