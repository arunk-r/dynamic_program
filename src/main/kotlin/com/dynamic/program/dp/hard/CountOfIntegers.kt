package com.dynamic.program.dp.hard

/**'
 * https://leetcode.com/problems/count-of-integers/description/
 * 2719. Count of Integers
 * Hard
 * 456
 * 10
 * company
 * Amazon
 * DE Shaw
 * You are given two numeric strings num1 and num2 and two integers max_sum and min_sum. We denote an integer x to be good if:
 *
 * num1 <= x <= num2
 * min_sum <= digit_sum(x) <= max_sum.
 * Return the number of good integers. Since the answer may be large, return it modulo 109 + 7.
 *
 * Note that digit_sum(x) denotes the sum of the digits of x.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = "1", num2 = "12", min_sum = 1, max_sum = 8
 * Output: 11
 * Explanation: There are 11 integers whose sum of digits lies between 1 and 8 are 1,2,3,4,5,6,7,8,10,11, and 12. Thus, we return 11.
 * Example 2:
 *
 * Input: num1 = "1", num2 = "5", min_sum = 1, max_sum = 5
 * Output: 5
 * Explanation: The 5 integers whose sum of digits lies between 1 and 5 are 1,2,3,4, and 5. Thus, we return 5.
 *
 *
 * Constraints:
 *
 * 1 <= num1 <= num2 <= 1022
 * 1 <= min_sum <= max_sum <= 400
 */
class CountOfIntegers {
    fun count(num1: String, num2: String, min_sum: Int, max_sum: Int): Int {
        return goodIntegers(num2, min_sum, max_sum) - goodIntegers(subtractOne(num1), min_sum, max_sum)
    }

    private fun goodIntegers(num: String, min_sum: Int, max_sum: Int): Int {
        val dp = Array(2) { Array(23) { IntArray(200) { -1 } } }
        val str = build(num)
        return goodIntegers(str, false, 0, 0, min_sum, max_sum, dp)
    }

    private fun goodIntegers(s: String, isSmaller: Boolean, i: Int, sum: Int, min_sum: Int, max_sum: Int, dp: Array<Array<IntArray>>): Int {
        if (i == s.length) {
            return if (sum in min_sum..max_sum) 1 else 0
        }
        val idx = if (isSmaller) 1 else 0
        if (dp[idx][i][sum] == -1) {
            var ans = 0
            val end = if (isSmaller) s[i] - '0' else 9
            for (j in 0..end) {
                val nextSmall = if (isSmaller) true else j == end
                ans += goodIntegers(s, nextSmall, j + 1, sum + j, min_sum, max_sum, dp)
            }
            dp[idx][i][sum] = ans
        }
        return dp[idx][i][sum]
    }

     private fun build(s: String): String {
        val len = s.length
        var newS = ""
        for (j in 0 until (23 - len)) {
            newS += '0'
        }
        return newS + s
    }

     private fun subtractOne(s: String): String {
        val intVal = s.toInt() - 1
        return intVal.toString()
    }
}
fun main() {

}
