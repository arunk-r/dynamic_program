package com.dynamic.program.strings_arrays.easy

class AddBinary {
    fun addBinary(a: String, b: String): String {
        if(a.length > b.length) return addBinary(b, a)
        val x = a.reversed()
        val y = b.reversed()
        var i = 0
        val buf = StringBuffer()
        var carry = ' '
        var sum = ' '
        while(i < x.length) {
            var newCarry = '0'
            if(x[i] == y[i] && x[i] == '1') {
                newCarry = '1'
                sum = '0'
            } else {
                if(x[i] == '1' || y[i] == '1') {
                    sum = '1'
                } else {
                    sum = '0'
                }
            }
            if(carry == '1' && sum == '0') {
                sum = '1'
            } else {
                carry = '1'
                sum = '0'
            }
            if(newCarry == '1') {
                carry = '1'
            }
            buf.append(sum)
            i++
        }
        while(i < b.length) {
            if(y[i] == carry && y[i] == '1') {
                carry = '1'
                sum = '0'
            } else {
                carry = '0'
                if(y[i] == '1') {
                    sum = '1'
                } else {
                    sum = '0'
                }
            }
            buf.append(sum)
            i++
        }
        if(carry == '1') {
            buf.append(carry)
        }
        buf.reverse()
        return buf.toString()
    }
}

fun main() {
    println(AddBinary().addBinary("1010", "1011"))
}
