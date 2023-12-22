package com.dynamic.program.backtracking.hard

import com.dynamic.program.linked_list.reverse
import java.util.function.Consumer


class ExpressionAddOperators {
    fun addOperators(num: String, target: Int): List<String> {
        val res = ArrayList<String>()

        fun partition(start: Int, path: String, sum: Long, pro: Long) {
            if (start == num.length) {
                if (sum == target.toLong()) {
                    res.add(path)
                }
                return
            }
            for (i in start until num.length) {
                val s = num.substring(start..i)
                val v = s.toLong()
                // invalid case: 1 + 05
                if (s.length > 1 && s[0] == '0')
                    break

                if (path.isEmpty()) {
                    partition(i + 1, s, v, v)
                } else {
                    partition(i + 1, "$path+$s", sum + v, v)
                    partition(i + 1, "$path-$s", sum - v, -v)
                    partition(i + 1, "$path*$s", sum - pro + pro * v, pro * v)
                }
            }
        }

        partition(0, "", 0L, 0L)

        return res.toList()
    }
}
