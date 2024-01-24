package com.dynamic.program.strings_arrays.medium

import java.util.*
import kotlin.math.abs


class PatternMatch {
    fun getPattern(s: String): String {
        if(s.toDouble() == 0.0) return ""
        val res = IntArray(s.length+1)
        val newS = s.substring(1)
        var i = 0
        var j = -1
        res[0] = -1
        var patternLength = 0
        while(i < newS.length) {
            if(j == -1 || newS[i] == newS[j]) {
                i++
                j++
                res[i] = j
                if(res[i] == 0) patternLength++
                else break
            } else {
                j = res[j]
            }
        }
        return if(patternLength == newS.length)  s
        else ".(" + newS.substring(0, patternLength) + ')'
    }

    fun countSubarrays(arr: IntArray): IntArray {
        val stack = Stack<Int>()
        val ans = IntArray(arr.size)
        for (i in arr.indices) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                ans[i] += ans[stack.pop()]
            }
            stack.push(i)
            ans[i]++
        }
        stack.clear()
        val temp = IntArray(arr.size)
        for (i in arr.indices.reversed()) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                val idx = stack.pop()
                ans[i] += temp[idx]
                temp[i] += temp[idx]
            }
            stack.push(i)
            temp[i]++
        }
        return ans
    }
    fun fractionToDecimal(numerator: Int, denominator: Int): String {
        if (numerator == 0) {
            return "0"
        }
        val res = StringBuilder()
        // "+" or "-"
        if ((numerator > 0) xor (denominator > 0))
            res.append( "-")
        var num = abs(numerator.toLong().toDouble()).toLong()
        val den = abs(denominator.toLong().toDouble()).toLong()

        // integral part
        res.append(num / den)
        num %= den
        if (num == 0L) {
            return res.toString()
        }

        // fractional part
        res.append(".")
        val map = HashMap<Long, Int>()
        map[num] = res.length
        while (num != 0L) {
            num *= 10
            res.append(num / den)
            num %= den
            if (map.containsKey(num)) {
                val index = map[num]!!
                res.insert(index, "(")
                res.append(")")
                break
            } else {
                map[num] = res.length
            }
        }
        return res.toString()
    }
}

fun main() {
    //println(PatternMatch().getPattern(".012012012012012012"))
    println(PatternMatch().countSubarrays(intArrayOf(3,4,1,6,2)).toList())
}
