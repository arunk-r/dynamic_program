package com.dynamic.program.design.medium.arrays

class TicTacToe(val n: Int) {
    val arr = Array(n) { IntArray(n) { -1 } }
    private var rows: IntArray = IntArray(n)
    private var cols: IntArray = IntArray(n)
    private var diagonal = 0
    private var antiDiagonal = 0
    fun move(row: Int, col: Int, player: Int): Int {
        val modify = if (player == 1) 1 else -1
        rows[row] += modify
        cols[col] += modify
        if (row == col) {
            diagonal += modify
        }
        if (row + col == n - 1) {
            antiDiagonal += modify
        }
        return when {
            rows[row] == n || cols[col] == n || diagonal == n || antiDiagonal == n -> 1
            rows[row] == -n || cols[col] == -n || diagonal == -n || antiDiagonal == -n -> 2
            else -> 0
        }
    }

    private fun ticTacToe(player: Int): Boolean {
        var r = 0
        var c = 0
        var cnt = 0
        while (c < n) {
            if (arr[r][c++] == player) {
                cnt++
            }
        }
        if (cnt == n) return true
        c--
        cnt = 0
        while (c >= 0) {
            if (arr[r][c] == player) {
                cnt++
            }
            r++
            c--
        }
        r--
        c++
        if (cnt == n) return true
        cnt = 0
        while (c < n) {
            if (arr[r][c++] == player) {
                cnt++
            }
        }
        c--
        if (cnt == n) return true
        cnt = 0
        while (c >= 0) {
            if (arr[r][c] == player) {
                cnt++
            }
            r--
            c--
        }
        return cnt == n
    }

}

fun main() {
    val obj = TicTacToe(2)
    println(obj.move(0, 1, 2))
    println(obj.move(1, 0, 1))
    println(obj.move(1, 1, 2))
    /*val obj = TicTacToe(3)
    println(obj.move(0,0,1))
    println(obj.move(0,2,2))
    println(obj.move(2,2,1))
    println(obj.move(1,1,2))
    println(obj.move(2,0,1))
    println(obj.move(1,0,2))
    println(obj.move(2,1,1))*/
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * var obj = TicTacToe(n)
 * var param_1 = obj.move(row,col,player)
 */
