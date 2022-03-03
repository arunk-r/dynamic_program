package com.dynamic.program.medium.next_greater_element

fun nextGreaterElement(array: MutableList<Int>): MutableList<Int> {
    // Write your code here.
    val result = MutableList(array.size) {-1}
    val stack = mutableListOf<Int>()

    for (idx in 0 until (2 * array.size)) {
        val circularIdx = idx % array.size

        while (stack.isNotEmpty() && array[stack[stack.size -1]] < array[circularIdx]) {
            val top = stack.removeAt(stack.size - 1)
            result[top] = array[circularIdx]
        }
        stack.add(circularIdx)
    }
    return result
}

fun main() {
    println(nextGreaterElement(mutableListOf(2, 5, -3, -4, 6, 7, 2)))
}