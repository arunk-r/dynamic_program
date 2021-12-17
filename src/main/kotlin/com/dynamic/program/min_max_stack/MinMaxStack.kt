package com.dynamic.program.min_max_stack

import kotlin.math.max
import kotlin.math.min

// Feel free to add new properties and methods to the class.
open class MinMaxStack() {
    val minMaxStack = mutableListOf<Map<String, Int>>()
    val stack = mutableListOf<Int>()
    fun peek(): Int? {
        // Write your code here.
        return stack[stack.size -1]
    }

    fun pop(): Int? {
        // Write your code here.
        minMaxStack.removeAt(stack.size -1)
        return stack.removeAt(stack.size -1)
    }

    fun push(number: Int) {
        // Write your code here.
        val newMinMax = mutableMapOf<String, Int>("min" to number, "max" to number)
        if (minMaxStack.size > 0) {
            val lastMinMax = minMaxStack[minMaxStack.size -1]
            newMinMax["min"] = min(number, lastMinMax["min"]!!)
            newMinMax["max"] = max(number, lastMinMax["max"]!!)
        }
        minMaxStack.add(newMinMax)
        this.stack.add(number)
    }

    fun getMin(): Int? {
        // Write your code here.
        return minMaxStack[minMaxStack.size -1] ["min"]
    }

    fun getMax(): Int? {
        // Write your code here.
        return minMaxStack[minMaxStack.size -1] ["max"]
    }
}
