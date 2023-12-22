package com.dynamic.program.strings_arrays.medium

class MaximumSwap {
    fun maximumSwap(num: Int): Int {
        val s = num.toString()
        var minIdx = findSmallNumber(s)
        if(minIdx == -1) return num
        val maxIdx = findBigNumber(s, minIdx)
        //println("$minIdx, $maxIdx")
        minIdx = findNextSmallNumber(s,minIdx, maxIdx)
        //println("$minIdx, $maxIdx")
        val c = s.toCharArray()
        swap(c, minIdx, maxIdx)
        return c.joinToString("").toInt()
    }

    private fun swap(s: CharArray, i: Int, j: Int) {
        val t = s[i]
        s[i] = s[j]
        s[j] = t
    }

    private fun findNextSmallNumber(s: String, idx: Int, maxIdx: Int): Int {
        for (i in 0 .. idx) {
            if (s[i] < s[maxIdx])
                return i
        }
        return idx
    }

    private fun findSmallNumber(s: String): Int {
        for(i in 0 until s.length - 1) {
            if(s[i] < s[i+1]) {
                return i
            }
        }
        return -1
    }

    private fun findBigNumber(s: String, idx: Int): Int {
        var maxi = idx
        for (i in idx until s.length) {
            if (s[maxi] <= s[i])
                maxi = i
        }
        return maxi
    }
}

fun main() {
    println(MaximumSwap().maximumSwap(99901))
}
