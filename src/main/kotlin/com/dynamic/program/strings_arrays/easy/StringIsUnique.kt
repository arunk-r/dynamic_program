package com.dynamic.program.strings_arrays.easy

class StringIsUnique {
    fun check(s: String): Boolean {
        var mask = 0
        for (c in s) {
            if (mask and (1 shl (c - 'a')) > 0) return false
            mask = mask or (1 shl (c - 'a'))
        }

        return true
    }
}

fun  main() {
    println(StringIsUnique().check("abccaad"))
    println(StringIsUnique().check("abcd"))
}
