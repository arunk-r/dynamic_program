package com.dynamic.program.bit.medium

class RangeBitwiseAnd {
    fun rangeBitwiseAnd(left: Int, right: Int): Int {
        val lbits = left.toUInt().toString(2).padStart(32, '0')
        val rbits = right.toUInt().toString(2).padStart(32, '0')
        val buf = StringBuffer()
        for(i in 0 until 32) {
            if(lbits[i] == rbits[i]) {
                buf.append(rbits[i])
            }
            else break
        }
        return java.math.BigInteger(buf.toString().padEnd(32, '0'), 2).toInt()
    }
}

fun main() {
    println(RangeBitwiseAnd().rangeBitwiseAnd(5, 7))
}
