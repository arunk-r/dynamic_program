package com.dynamic.program.very_hard.apartment_hunting

/**
 *Minimum Swaps To Make A Binary String Palindrome
 */

fun minimumSwapsToMakeABinaryStringPalindrome(s: String): Int {
    if (s.isEmpty())
        return -1
    var leftPt = 0
    var rightPt = s.length - 1
    var swapCnt = 0
    var newString = s
    while (leftPt < rightPt) {
        if (newString[leftPt] == newString[rightPt]) {
            leftPt++
            rightPt--
        } else {
            if (newString[leftPt] == '0') {
                val idx = findNextElementToSwap(newString, '1', leftPt, rightPt)
                if (idx != -1) {
                    newString = if (s[leftPt] != s[idx]) {
                        swap(newString, leftPt, idx)
                    } else {
                        swap(newString, idx, rightPt)
                    }
                    swapCnt++
                    if (newString == newString.reversed()) {
                        return swapCnt
                    }
                    leftPt++
                    rightPt--
                } else {
                    break
                }
            } else {
                val idx = findNextElementInBackwardsToSwap(newString, '1', leftPt, rightPt)
                if (idx != -1) {
                    newString = swap(newString, idx, rightPt)
                    swapCnt++
                    if (newString == newString.reversed()) {
                        return swapCnt
                    }
                    leftPt++
                    rightPt--
                } else {
                    break
                }
            }
        }
    }
    return -1
}

fun findNextElementToSwap(s: String, c: Char, startIdx: Int, endIdx: Int): Int {
    val substring = s.substring(startIdx, endIdx+1)
    if (substring.length == 3) {
        if (substring[0] != substring[2]) {
            return startIdx+1
        }
    }
    var index = -1
    for (i in substring.indices) {
        if (substring[i] == c) {
            index = startIdx + i
            break
        }
    }
    return index
}

fun findNextElementInBackwardsToSwap(s: String, c: Char, startIdx: Int, endIdx: Int): Int {
    val substring = s.substring(startIdx+1, endIdx+1)
    var index = -1
    var newEnd = endIdx
    if (substring.length == 3) {
        newEnd--
    }
    for (i in substring.length - 1 downTo 0) {
        if (substring[i] == c) {
            index = newEnd - i
        }
    }
    return index
}

fun swap(s: String, fromIdx: Int, toIdx: Int): String {
    val chars = s.toCharArray()
    val t = chars[fromIdx]
    chars[fromIdx] = chars[toIdx]
    chars[toIdx] = t

    return String(chars)
}

fun main() {
    //println(minimumSwapsToMakeABinaryStringPalindrome("0100101"))
    //println(minimumSwapsToMakeABinaryStringPalindrome("0101001"))
    println(minimumSwapsToMakeABinaryStringPalindrome("11000"))
}