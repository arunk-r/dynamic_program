package com.dynamic.program.strings_arrays.hard

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
    val BILLION = 1000000000
    val MILLION = 1000000
    val THOUSAND = 1000
    val HUNDARD = 100

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
    fun numberToWords(num: Int): String {
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
}

fun main() {
    //println(IntegerToEnglishWords().numberToWords(123))
    //println(IntegerToEnglishWords().numberToWords(12345))
    println(IntegerToEnglishWords().numberToWords(1234567))
}
