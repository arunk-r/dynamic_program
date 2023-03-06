package com.dynamic.program.graphs

/**
 * Open the Lock
 *
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 *
 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
 *
 * Example 3:
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation: We cannot reach the target without getting stuck.
 *
 *
 * Constraints:
 * 1 <= deadends.length <= 500
 * deadends\[i].length == 4
 * target.length == 4
 * target will not be in the list deadends.
 * target and deadends\[i] consist of digits only.
 *
 */
class OpenTheLock {
    fun openLock(deadends: Array<String>, target: String): Int {
        val ends = deadends.toSet()
        val initials = "0000"
        val q = ArrayDeque<String>()
        val distance = hashMapOf<String, Int>()
        if (ends.contains(initials)) return -1
        distance[initials] = 0
        q.addLast(initials)

        while (q.isNotEmpty()) {
            val curKey = q.removeFirst()
            if (curKey == target) return distance[curKey]!!
            for(i in curKey.indices) {
                for (nextCode in listOf(getNext(i, curKey), getPrev(i,curKey))) {
                    if (!ends.contains(nextCode) && !distance.containsKey(nextCode)) {
                        q.addLast(nextCode)
                        distance[nextCode] = distance[curKey]!!+1
                    }
                }
            }
        }
        return -1
    }

    private fun getNext(i: Int, str: String): String {
        val c = str.toCharArray()
        c[i] = if (c[i] == '9') '0' else c[i]+1
        return c.joinToString("")
    }
    private fun getPrev(i: Int, str: String): String {
        val c = str.toCharArray()
        c[i] = if (c[i] == '0') '9' else c[i]-1
        return c.joinToString("")
    }

    fun openLock1(deadends: Array<String>, target: String): Int {
        val seen = mutableSetOf<String>()
        deadends.forEach{ d ->
            seen.add(d)
            if (d == "0000") {
                return -1
            }
        }

        seen.add("0000")

        val q = ArrayDeque<Pair<String, Int>>()
        q.addLast(Pair("0000", 0))
        while(q.isNotEmpty()) {
            val cur = q.removeFirst()
            if (cur.first == target) {
                return cur.second
            }
            getNextValue(cur.first).forEach{ next ->
                if(!seen.contains(next)) {
                    q.addLast(Pair(next, cur.second +1))
                    seen.add(next)
                }
            }
        }

        return -1
    }

    private fun getNextValue(str: String): List<String> {
        val ans = mutableListOf<String>()
        for (i in str.indices) {
            val num = str[i] - '0'
            val nextNum = (num + 1 + 10) % 10
            val prevNum = (num - 1 + 10) % 10
            ans.add("${str.substring(0,i)}$nextNum${str.substring(i+1)}")
            ans.add("${str.substring(0,i)}$prevNum${str.substring(i+1)}")
        }
        return ans
    }
}

fun main() {
    println(OpenTheLock().openLock(arrayOf("0201","0101","0102","1212","2002"), "0202"))
}
