package com.dynamic.program.strings_arrays.medium

/**
 * 93. Restore IP Addresses
 * Medium
 *
 * company
 * Amazon
 * company
 * TikTok
 * company
 * Google
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 *
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * s consists of digits only.
 */
class RestoreIPAddresses {
    val lst = mutableListOf<String>()
    fun restoreIpAddresses(s: String): List<String> {
        combination(0, 0, s, "")
        return lst
    }

    //o(3^n) where n is input size
    private fun combination(i: Int, dots: Int, s: String, cur: String) {
        println(cur)
        if (i == s.length && dots == 4) {
            lst.add(cur.substring(0, cur.length - 1))
            return
        } else if (dots > 4) {
            return
        }
        for (j in i until (minOf(i + 3, s.length))) {
            val v = s.substring(i, j + 1)
            if (v[0] != '0' && v.toInt() < 256) {
                combination(j + 1, dots + 1, s, "$cur$v.")
            }
        }
    }
}

fun main() {
    println(RestoreIPAddresses().restoreIpAddresses("25525511135"))
}
