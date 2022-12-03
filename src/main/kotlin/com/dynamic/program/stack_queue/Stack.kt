package com.dynamic.program.stack_queue

val arrays = MutableList(3) { -1 }

class Stack1 {
    private var indexRef = 0

    fun pop(): Int? {
        if (indexRef == 0) return null
        return arrays.removeAt(decrementIndex())
    }

    fun push(v: Int) {
        arrays[incrementIndex()] = v
    }

    fun isEmpty(): Boolean = indexRef == 0

    private fun incrementIndex(): Int {
        indexRef += 3
        return indexRef
    }

    private fun decrementIndex(): Int {
        val v = indexRef
        indexRef -= 3
        if (indexRef < 0) {
            indexRef = 0
        }
        return v
    }
}

fun main() {
    val s1 = Stack1()
    println(s1.pop())
    println(s1.push(1))
    println(s1.pop())
    println(s1.pop())
}

