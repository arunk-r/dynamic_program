package com.dynamic.program.dp.mcm

/**
 * 87. Scramble String
Hard
We can scramble a string s to get a string t using the following algorithm:

If the length of the string is 1, stop.
If the length of the string is > 1, do the following:
Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
Apply step 1 recursively on each of the two substrings x and y.
Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.



Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Explanation: One possible scenario applied on s1 is:
"great" --> "gr/eat" // divide at random index.
"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of them.
"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
The algorithm stops now, and the result string is "rgeat" which is s2.
As one possible scenario led s1 to be scrambled to s2, we return true.
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
Example 3:

Input: s1 = "a", s2 = "a"
Output: true


Constraints:

s1.length == s2.length
1 <= s1.length <= 30
s1 and s2 consist of lowercase English letters.
 */
class ScrambleString {
    val map = hashMapOf<String, Boolean>()
    fun isScramble(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false
        else if (s1.isEmpty() || s2.isEmpty()) return false
        else if (s1 == s2) {
            return true
        }
        val key = key(s1, s2)
        if (map.containsKey(key) && map.keys.last() != key) {
            return map[key]!!
        }
        val n = s1.length
        val count = IntArray(26)
        for(i in s1.indices)
        {
            count[s1[i]-'a']++;
            count[s2[i]-'a']--;
        }

        count.forEach { v ->
            if (v != 0 ) {
                map[key] = false
                return false
            }
        }

        var flag = false
        for (i in 1 until n) {
            if (condition1(s1, s2, i, n) ||
                condition2(s1, s2, i, n)
            ) {
                flag = true
                break
            }
        }
        map[key] = flag
        return flag
    }

    private fun condition1(s1: String, s2: String, i: Int, n: Int): Boolean {
        val s1Left = s1.substring(0, i)
        val s1Right = s1.substring(i, i + (n - i))

        val s2Left = s2.substring(0, n - i)
        val s2Right = s2.substring(n - i, (n - i) + i)

        val leftRight = isScramble(s1Left, s2Right)
        map[key(s1Left, s2Right)] = leftRight

        val rightLeft = isScramble(s1Right, s2Left)
        map[key(s1Right, s2Left)] = rightLeft

        return leftRight && rightLeft

    }

    private fun condition2(s1: String, s2: String, i: Int, n: Int): Boolean {
        val s1Left = s1.substring(0, i)
        val s1Right = s1.substring(i, i + (n - i))

        val s2Left = s2.substring(0, i)
        val s2Right = s2.substring(i, i + (n - i))

        val leftLeft = isScramble(s1Left, s2Left)
        map[key(s1Left, s2Left)] = leftLeft


        val rightRight = isScramble(s1Right, s2Right)
        map[key(s1Right, s2Right)] = rightRight

        return leftLeft && rightRight

    }

    private fun key(s1: String, s2: String) = "$s1$$s2$"
}

fun main() {
    //println(ScrambleString().isScramble("great", "rgeat"))
    //println(ScrambleString().isScramble("abcde", "caebd"))
    println(ScrambleString().isScramble("xstjzkfpkggnhjzkpfjoguxvkbuopi", "xbouipkvxugojfpkzjhnggkpfkzjts"))
}