package com.dynamic.program.strings_arrays.hard

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * 273. Integer to English Words
 * Hard
 *
 * company
 * Apple
 * company
 * Amazon
 * company
 * Microsoft
 * Convert a non-negative integer num to its English words representation.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 *
 * Constraints:
 *
 * 0 <= num <= 231 - 1
 */
class IntegerToEnglishWords {
    private val BILLION = 1000000000
    private val MILLION = 1000000
    private val THOUSAND = 1000
    private val HUNDRED = 100
    // Arrays to store words for ones and tens places
    private val ones = arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen")

    private val tens = arrayOf("", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

    fun numberToWords(num: Int, result: String = ""): String {
        return when {
            num == 0 && result == "" -> "Zero"
            num == 0 -> result.trim()
            num >= BILLION -> numberToWords(num % BILLION, numberToWords(num / BILLION) + " Billion ")
            num >= MILLION -> numberToWords(num % MILLION, result + numberToWords(num / MILLION) + " Million ")
            num >= THOUSAND -> numberToWords(num % THOUSAND, result + numberToWords(num / THOUSAND) + " Thousand ")
            num >= HUNDRED -> numberToWords(num % HUNDRED, result + numberToWords(num / HUNDRED) + " Hundred ")
            num >= 20 -> numberToWords(num % 10, result + tens[num / 10] + " ")
            else -> result + ones[num]
        }
    }

    fun one(num: Int) : String {
        return when (num) {
            1 -> " one "
            2 -> " two "
            3 -> " three "
            4 -> " four "
            5 -> " five "
            6 -> " six "
            7 -> " seven "
            8 -> " eight "
            9 -> " nine "
            else -> ""
        }
    }
    fun teens(num: Int) : String {
        return when (num) {
            10 -> "ten "
            11 -> "eleven "
            12 -> "twelve "
            13 -> "thirteen "
            14 -> "fourteen "
            15 -> "fifteen "
            16 -> "sixteen "
            17 -> "seventeen "
            18 -> "eighteen "
            19 -> "nineteen "
            else -> ""
        }
    }
    fun ten(num: Int): String {
        return when (num) {
            20 -> " twenty "
            30 -> " thirty "
            40 -> " forty "
            50 -> " fifty "
            60 -> " sixty "
            70 -> " seventy "
            80 -> " eighty "
            90 -> " ninety "
            else -> ""
        }
    }
    fun two(num: Int): String {
        if (num == 0) return ""
        else if (num < 10) return one(num)
        else if (num < 19) return teens(num)
        else {
            val ten = num / 10
            val n = num - ten * 10
            if (n != 0) {
                return ten(ten * 10) + " " + one(n)
            } else {
                return ten(ten)
            }
        }
    }
    fun three(num: Int): String {
        val hundred = num / 100
        val rest = num - hundred * 100
        var res = ""
        if (hundred * rest != 0) {
            res = one(hundred) + " hundred " + two(rest)
        } else if(hundred == 0 && rest != 0) {
            res = two(rest)
        } else {
            res = one(hundred) + "hundred"
        }
        return res
    }
    fun numberToWords1(num: Int): String {
        if (num == 0)
        return "zero"
        val billion = num / BILLION
        val million = (num - billion * BILLION ) / MILLION
        val thousand = (num - billion * BILLION - million * MILLION) / THOUSAND
        val rest = num - billion * BILLION - million * MILLION - thousand * THOUSAND

        var result = ""
        if (billion != 0) {
            result = three(billion) + " billion "
        }
        if (million != 0) {
            result += three(million) + " million "
        }
        if (thousand != 0) {
            result += three(thousand) + " thousand "
        }
        if (rest != 0) {
            result += three(rest)
        }
        return result
    }

    fun countOfPairs(n: Int, x: Int, y: Int): LongArray {
        var x = x
        var y = y
        if (x > y) {
            val t = x
            x = y
            y = t
        }
        val result = LongArray(n)
        for (i in 1..n) {
            result[0] += 2L // go left and right
            result[min((i - 1), (abs((i - y)) + x))]-- // reach 1 then stop
            result[min((n - i), (abs((i - x)) + 1 + n - y))]-- // reach n then stop
            result[min(abs((i - x)), (abs((y - i)) + 1))
                ]++ // reach x then split
            result[min((abs((i - x)) + 1), abs((y - i)))
                ]++ // reach y then split
            val r = (max((x - i), 0) + max((i - y), 0))
            result[r + (y - x + 0) / 2]-- // i -> x -> y <- x
            result[r + (y - x + 1) / 2]-- // i -> y -> x <- y
        }
        for (i in 1 until n) result[i] += result[i - 1]
        return result
    }
}

fun main() {
    //println(IntegerToEnglishWords().numberToWords(123))
    //println(IntegerToEnglishWords().numberToWords(12345))
    println(IntegerToEnglishWords().numberToWords(1234567))
}
