package com.dynamic.program.medium.sort_stack

fun sortStack(stack: MutableList<Int>): MutableList<Int> {
    // Write your code here.
    if (stack.isEmpty()) return stack

    val top = stack.removeAt(stack.size -1)
    sortStack(stack)

    insertInOrder(stack, top)
    return stack
}

fun insertInOrder(stack: MutableList<Int>, value: Int) {
    if (stack.isEmpty() || stack[stack.size - 1] <= value) {
        stack.add(value)
        return
    }
    val top = stack.removeAt(stack.size - 1)
    insertInOrder(stack, value)
    stack.add(top)
}

fun main() {
    println(sortStack(mutableListOf(-5, 2, -2, 4, 3, 2, 1)))
}