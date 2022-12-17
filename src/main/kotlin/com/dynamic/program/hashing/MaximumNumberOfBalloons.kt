package com.dynamic.program.hashing

/**
 * Maximum Number of Balloons
 *
 * Solution
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 *
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 *
 * Example 1:
 *Input: text = "nlaebolko"
 * Output: 1
 *
 * Example 2:
 * Input: text = "loonbalxballpoon"
 * Output: 2
 *
 * Example 3:
 * Input: text = "leetcode"
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= text.length <= 10^4
 * text consists of lower case English letters only.
 */


fun maxNumberOfBalloons(text: String): Int {
    val targetTxt = "balloon"
    val map = hashMapOf<Char, Int>()
    for(c in targetTxt) {
        map[c] = 0
    }
    for(c in text) {
        if (map.containsKey(c)) {
            map[c] = map[c]?.plus(1) ?: 1
        }
    }
    var cnt = Int.MAX_VALUE
    map.forEach{ (k,v) ->
        cnt = if (k == 'l' || k == '0') {
            Math.min(cnt, v/2)
        } else {
            Math.min(cnt, v)
        }
    }

    return cnt
}

fun main() {
    println(maxNumberOfBalloons("ballon"))
}
