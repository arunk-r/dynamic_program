package com.dynamic.program.top.hundred.medium

class GroupShifterStrings {
    fun hashKey(s: String): String {
        val buf = StringBuffer()
        for(i in 1 until s.length) {
            val mod = (s[i] - s[i-1]).toString()
            buf.append(mod)
        }
        return buf.toString()
    }
}
