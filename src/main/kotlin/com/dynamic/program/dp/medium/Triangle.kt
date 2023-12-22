package com.dynamic.program.dp.medium

class Triangle {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        if(triangle.isEmpty()) return -1

        val n = triangle.size
        val m = triangle[n-1].size
        val result = Array(n){IntArray(m){Int.MAX_VALUE}}
        result[0][0] = triangle[0][0]
        for(i in 0 until n - 1) {
            for(j in triangle[i].indices) {
                result[i+1][j] = minOf(result[i+1][j], result[i][j] + triangle[i+1][j])
                result[i+1][j+1] = minOf(result[i+1][j+1], result[i][j] + triangle[i+1][j+1])
            }
        }
        var min = Int.MAX_VALUE
        for(i in 0 until m) {
            min = minOf(min, result[n-1][i])
        }
        return min
    }
}

fun main() {
    //println(Triangle().minimumTotal(listOf(listOf(2), listOf(3,4), listOf(6,5,7), listOf(4,1,8,3))))
    println(Triangle().minimumTotal(listOf(listOf(-10))))
}
