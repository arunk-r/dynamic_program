package com.dynamic.program.arrays.hard

import java.util.*


/**
 * 2281. Sum of Total Strength of Wizards
Hard

company
Amazon
As the ruler of a kingdom, you have an army of wizards at your command.

You are given a 0-indexed integer array strength, where strength[i] denotes the strength of the ith wizard. For a contiguous group of wizards (i.e. the wizards' strengths form a subarray of strength), the total strength is defined as the product of the following two values:

The strength of the weakest wizard in the group.
The total of all the individual strengths of the wizards in the group.
Return the sum of the total strengths of all contiguous groups of wizards. Since the answer may be very large, return it modulo 109 + 7.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: strength = [1,3,1,2]
Output: 44
Explanation: The following are all the contiguous groups of wizards:
- [1] from [1,3,1,2] has a total strength of min([1]) * sum([1]) = 1 * 1 = 1
- [3] from [1,3,1,2] has a total strength of min([3]) * sum([3]) = 3 * 3 = 9
- [1] from [1,3,1,2] has a total strength of min([1]) * sum([1]) = 1 * 1 = 1
- [2] from [1,3,1,2] has a total strength of min([2]) * sum([2]) = 2 * 2 = 4
- [1,3] from [1,3,1,2] has a total strength of min([1,3]) * sum([1,3]) = 1 * 4 = 4
- [3,1] from [1,3,1,2] has a total strength of min([3,1]) * sum([3,1]) = 1 * 4 = 4
- [1,2] from [1,3,1,2] has a total strength of min([1,2]) * sum([1,2]) = 1 * 3 = 3
- [1,3,1] from [1,3,1,2] has a total strength of min([1,3,1]) * sum([1,3,1]) = 1 * 5 = 5
- [3,1,2] from [1,3,1,2] has a total strength of min([3,1,2]) * sum([3,1,2]) = 1 * 6 = 6
- [1,3,1,2] from [1,3,1,2] has a total strength of min([1,3,1,2]) * sum([1,3,1,2]) = 1 * 7 = 7
The sum of all the total strengths is 1 + 9 + 1 + 4 + 4 + 4 + 3 + 5 + 6 + 7 = 44.
Example 2:

Input: strength = [5,4,6]
Output: 213
Explanation: The following are all the contiguous groups of wizards:
- [5] from [5,4,6] has a total strength of min([5]) * sum([5]) = 5 * 5 = 25
- [4] from [5,4,6] has a total strength of min([4]) * sum([4]) = 4 * 4 = 16
- [6] from [5,4,6] has a total strength of min([6]) * sum([6]) = 6 * 6 = 36
- [5,4] from [5,4,6] has a total strength of min([5,4]) * sum([5,4]) = 4 * 9 = 36
- [4,6] from [5,4,6] has a total strength of min([4,6]) * sum([4,6]) = 4 * 10 = 40
- [5,4,6] from [5,4,6] has a total strength of min([5,4,6]) * sum([5,4,6]) = 4 * 15 = 60
The sum of all the total strengths is 25 + 16 + 36 + 36 + 40 + 60 = 213.


Constraints:

1 <= strength.length <= 105
1 <= strength[i] <= 109
 */
class SumOfTotalStrengthOfWizards {
    fun totalStrength2(strength: IntArray): Int {
        val mod: Long = 1000000007
        var res: Long = 0
        val n: Int = strength.size

        val leftsum = LongArray(n + 1)
        val rightsum = LongArray(n + 1)
        val leftmul = LongArray(n + 1)
        val rightmul = LongArray(n + 1)
        val asc = Stack<Int>()

        for (i in 0 until n) {
            leftsum[i + 1] = (leftsum[i] + strength[i]) % mod
            leftmul[i + 1] = (leftmul[i] + (i + 1).toLong() * strength[i]) % mod
        }

        for (i in n - 1 downTo 0) {
            rightsum[i] = (rightsum[i + 1] + strength[i]) % mod
            rightmul[i] = (rightmul[i + 1] + (n - i).toLong() * strength[i]) % mod
        }

        // j is the exclusive right index
        for (j in 0..n) {
            while (!asc.empty() && (j == n || strength[asc.peek()] >= strength[j])) {
                val k = asc.pop()
                val i = if (asc.empty()) 0 else asc.peek() + 1
                val left = (mod + leftmul[k + 1] - leftmul[i] - i * (leftsum[k + 1] - leftsum[i]) % mod) % mod
                val right = (mod + rightmul[k + 1] - rightmul[j] - (n - j) * (rightsum[k + 1] - rightsum[j]) % mod) % mod
                val sum = (left * (j - k) + right * (k - i + 1)) % mod
                res = (res + sum * strength[k]) % mod
            }
            asc.push(j)
        }
        return res.toInt()

    }

    fun greedy(i: Int, j: Int, strength: IntArray, ps: IntArray, mem: Array<IntArray>): Int {
        println("$i, $j")
        if (mem[i][j] != -1) return 0
        if (i == j) {
            println("$i == $j > ${strength[i]} * ${strength[j]}")
            mem[i][j] = strength[i] * strength[j]
            return mem[i][j]
        } else {
            println("greedy($i, ${j - 1}, strength, ps, mem)")
            var l = greedy(i, j - 1, strength, ps, mem)
            if (i == 0) {
                println("getMin($i, $j, strength) * ps[j] > ${getMin(i, j, strength)} * ${ps[j]}")
                l += getMin(i, j, strength) * ps[j]
            } else {
                println("getMin($i, $j, strength) * (ps[j] - ps[i-1]) > ${getMin(i, j, strength)} * ${ps[j]} - ${ps[i - 1]})")
                l += getMin(i, j, strength) * (ps[j] - ps[i - 1])
            }
            println("greedy(${i + 1}, $j, strength, ps, mem)")
            var r = greedy(i + 1, j, strength, ps, mem)
            println("getMin(${i + 1}, $j, strength) * (ps[j] - ps[i]) > ${getMin(i, j, strength)} * ${ps[j]} - ${ps[i]})")
            r += getMin(i + 1, j, strength) * (ps[j] - ps[i])
            mem[i][j] = l + r
            return l + r
        }
    }

    fun totalStrength(strength: IntArray): Int {
        val mem = Array(strength.size) { IntArray(strength.size) { -1 } }

        val ps = IntArray(strength.size)

        for ((i, v) in strength.withIndex()) {
            if (i != 0) {
                ps[i] = ps[i - 1] + v
            } else {
                ps[i] = v
            }
        }
        return greedy(0, strength.size - 1, strength, ps, mem)
    }

    fun getMin(i: Int, j: Int, strength: IntArray): Int {
        var min = Int.MAX_VALUE
        for (k in i..j) {
            min = minOf(min, strength[k])
        }
        return min
    }

    fun totalStrength1(strength: IntArray): Int {
        var ans = 0

        val ps = IntArray(strength.size)

        for ((i, v) in strength.withIndex()) {
            ans += v * v
            if (i != 0) {
                ps[i] = ps[i - 1] + v
            } else {
                ps[i] = v
            }
        }

        val rightMin = IntArray(strength.size)
        var min = strength[strength.size - 1]
        for (i in strength.size - 1 downTo 0) {
            min = minOf(min, strength[i])
            rightMin[i] = min
        }
        var leftMin = Int.MAX_VALUE
        var rm = 0
        for (i in 0 until strength.size - 1) {

            leftMin = minOf(leftMin, strength[i])
            rm = rightMin[i + 1]

            if (i == 0) {
                ans += rm * (ps[ps.size - 1] - ps[0])
            } else if (i == strength.size - 2) {
                ans += leftMin * ps[i]
            } else {
                ans += leftMin * ps[i]
                ans += rm * (ps[ps.size - 1] - ps[i])
            }
        }
        ans += leftMin * ps[ps.size - 1]
        return ans
    }
}

fun main() {
    println(SumOfTotalStrengthOfWizards().totalStrength(intArrayOf(1, 3, 1, 2)))
    //println(SumOfTotalStrengthOfWizards().totalStrength(intArrayOf(5, 4, 6)))
}
