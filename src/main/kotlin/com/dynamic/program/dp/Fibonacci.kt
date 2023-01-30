package com.dynamic.program.dp

/**
 * Fibonacci
 * with remembering the value
 */
class Fibonacci {
    val map = hashMapOf<Int, Long>()
    fun fibonacci(n: Int): Long {
        if (n == 1) return 0
        if (n == 2) return 1
        //return fibonacci(n - 1) + fibonacci(n - 2) // why comment? run and check the difference
        if (map.containsKey(n)) {
            return map[n]!!
        } else {
            map.putIfAbsent(n, fibonacci(n - 1) + fibonacci(n - 2))
        }
        return map[n]!!
    }
}

fun main() {
    println(Fibonacci().fibonacci(75))
}