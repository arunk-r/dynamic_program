package com.dynamic.program.daily.challenge

class LongestAbsolutePath {
    data class Data(val found: Boolean, val str: String, val idx: Int)
    var max = 0
    fun path(s: String): Int {
        var i = 0
        val buf = StringBuffer()
        while (s[i] != '\n') {
            buf.append(s[i++])
        }
        findLongestPathCount(i, s, "\n\t")
        return max+buf.length
    }
    private fun findLongestPathCount(idx: Int, s: String, pattern: String): Int {
        if(idx == s.length) return 0
        val data = getPattern(idx, s, pattern)
        var ans = 0
        ans = if(data.found) {
            if (data.str.contains('.')){
                data.str.length+1
            } else {
                val len = findLongestPathCount(data.idx, s, pattern+"\t")
                if (len != 0) {
                    data.str.length+1
                } else {
                    findLongestPathCount(data.idx, s, pattern)
                }
            }
        } else {
            0
        }
        max = maxOf(max, ans)
        return ans
    }

    private fun getPattern(idx: Int, s: String, pattern: String): Data {
        if (idx + pattern.length > s.length) return Data(false, "", s.length)
        val newPattern = s.substring(idx, idx+ pattern.length)
        if(newPattern != pattern) return Data(false, "", idx)
        var i = idx + pattern.length
        val buf = StringBuffer()
        while (i < s.length && s[i] != '\n') {
            buf.append(s[i++])
        }
        return Data(true, buf.toString(), i)
    }
 }

fun main() {
    println(LongestAbsolutePath().path("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"))
    println(LongestAbsolutePath().path("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"))
}
