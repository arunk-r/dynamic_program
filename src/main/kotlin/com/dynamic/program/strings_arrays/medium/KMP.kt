package com.dynamic.program.strings_arrays.medium

/**
 * 
 */
class KMP {
    fun matchString(ransomNote: String, magazine: String): Boolean {
        val map = hashMapOf<Char, Int>()
        val prefix = IntArray(ransomNote.length){-1}
        for(i in ransomNote.indices) {
            val c = ransomNote[i]
            if (map[c] == null) {
                map[c] = i-1
            } else {
                prefix[i] = map[c]!! - 1
            }
        }

        var j = -1

        for(i in magazine.indices) {
            val mc = magazine[i]
            var rc = ransomNote[j+1]

            if(mc != rc) {
                while(mc != rc) {
                    j = map[rc]!!
                    if (j == -1) {
                        break
                    }else {
                        rc = ransomNote[j+1]
                    }
                }
            } else {
                j++
                if(j == ransomNote.length-1) return true
            }
        }

        return false
    }
}

fun main() {
    //println(KMP().matchString("a", "b"))
    //println(KMP().matchString("aa", "ab"))
    //println(KMP().matchString("aa", "aab"))
    println(KMP().matchString("aab", "aab"))
}
