package com.dynamic.program.dp.medium

class InterleavingString {
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        val count = IntArray(26)
        for(c in s2) {
            count[c-'a']++
        }
        val buf = StringBuffer()
        for(i in s3.length - 1 downTo 0) {
            val c = s3[i]
            if(count[c-'a'] == 0) {
                buf.append(c)
            } else {
                count[c-'a']--
            }
        }
        buf.reverse()
        println(buf.toString())
        return buf.toString() == s1
    }
}

fun main() {
    println(InterleavingString().isInterleave("aabcc", "dbbca", "aadbbcbcac"))
}
