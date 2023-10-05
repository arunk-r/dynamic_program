package com.dynamic.program.strings_arrays.easy

import java.util.TreeMap

/**
 * 13. Roman to Integer
Easy

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.



Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.
Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 3:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


Constraints:

1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
class RomanToInteger {
    fun intToRoman(num: Int): String {
        val map = TreeMap<Int, String> { x, y -> y - x }
        map[1] = "I"
        map[4] = "IV"
        map[5] = "V"
        map[9] = "IX"
        map[10] = "X"
        map[40] = "XL"
        map[50] = "L"
        map[90] = "XC"
        map[100] = "C"
        map[400] = "CD"
        map[500] = "D"
        map[900] = "CM"
        map[1000] = "M"
        var newNum = num
        val ans = StringBuffer()
        while (newNum > 0) {
            for ((intNum, romanSymbol) in map) {
                if (newNum >= intNum) {
                    ans.append(romanSymbol)
                    newNum -= intNum
                    break
                }
            }
        }
        return ans.toString()
    }

    fun romanToInt(s: String): Int {
        var sum = 0
        for (c in s) {
            when (c) {
                'I' -> sum += 1
                'V' -> sum += 5
                'X' -> sum += 10
                'L' -> sum += 50
                'C' -> sum += 100
                'D' -> sum += 500
                'M' -> sum += 1000
                else -> 0
            }
        }

        for (i in 0..s.length - 2) {
            when ("${s[i]}${s[i + 1]}") {
                "IV" -> sum -= 2
                "IX" -> sum -= 2
                "XL" -> sum -= 20
                "XC" -> sum -= 20
                "CD" -> sum -= 200
                "CM" -> sum -= 200
                else -> 0
            }
        }

        return sum
    }
}

fun main() {
    val obj = RomanToInteger()
    println(obj.intToRoman(20))
}
