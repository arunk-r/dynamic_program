package com.dynamic.program.top.hundred.medium

class MultiplyStrings {
    fun multiply(num1: String, num2: String): String {
        if(num1.length < num2.length) return multiply(num2, num1)

        val result = IntArray(num1.length + num2.length)
        var idx = num1.length + num2.length -1
        for(i in num1.length-1 downTo 0) {
            var tmpIdx = idx
            var carry = 0
            val n1 = num1[i] - '0'
            for(j in num2.length-1 downTo 0) {
                val n2 = num2[j] - '0'
                val multi = n1 * n2 + carry + result[tmpIdx]
                carry = multi / 10
                result[tmpIdx--] = (multi%10)
            }
            while(carry > 0) {
                carry += result[tmpIdx]
                result[tmpIdx--] += (carry % 10)
                carry /= 10
            }
            idx--
        }
        val buf = StringBuffer()
        var toStart = false
        for(v in result) {
            if(!toStart && v == 0) continue
            buf.append(v)
            toStart = true
        }
        return buf.toString()
    }
}

fun main() {
    println(MultiplyStrings().multiply("123", "456"))
}
