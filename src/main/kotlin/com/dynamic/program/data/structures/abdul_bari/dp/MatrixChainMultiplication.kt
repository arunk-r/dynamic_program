package com.dynamic.program.data.structures.abdul_bari.dp

class MatrixChainMultiplication {
    fun findMInCostToMultipleMatrix(values: IntArray): Int {
        val n = values.size
        val dp = Array(n) { IntArray(n) }
        for (d in 1 until n - 1) {
            for (r in 1 until n - d) {
                val c = r + d
                var min = Int.MAX_VALUE
                for (k in r until c) {
                    min = minOf(
                            min,
                            dp[r][k] + dp[k+1][c] + values[r-1] * values[k] * values[c]
                    )
                }
                dp[r][c] = min
            }
        }
        return dp[1][n - 1]
    }
}

fun main() {
    val mp = hashMapOf<Int, Int>()
    mp.values.sum()
    println(MatrixChainMultiplication().findMInCostToMultipleMatrix(intArrayOf(5, 4, 6, 2, 7)))
    println(MatrixChainMultiplication().findMInCostToMultipleMatrix(intArrayOf(1,2,3)))
    println(MatrixChainMultiplication().findMInCostToMultipleMatrix(intArrayOf(3,7,4,5)))
}
