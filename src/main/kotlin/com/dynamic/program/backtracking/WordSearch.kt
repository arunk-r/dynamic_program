package com.dynamic.program.backtracking

class WordSearch {
    val dir = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val seen = Array(board.size) { BooleanArray(board[0].size) }
        for (r in board.indices) {
            for ((c, v) in board[r].withIndex()) {
                if (v == word[0] && backTack(r, c, 1, word, board, seen)) {
                    return true
                }
            }
        }
        return false
    }

    private fun backTack(
        row: Int,
        col: Int,
        i: Int,
        word: String,
        board: Array<CharArray>,
        seen: Array<BooleanArray>
    ): Boolean {
        if (word.length == i) return true
        dir.forEach { (r, c) ->
            val nr = row + r
            val nc = col + c
            if (valid(nr, nc, board, seen)) {
                if (board[nr][nc] == word[i]) {
                    seen[nr][nc] = true
                    if (backTack(nr, nc, i + 1, word, board, seen)) {
                        return true
                    } else {
                        seen[nr][nc] = false
                    }
                }
            }
        }
        return false
    }

    private fun valid(row: Int, col: Int, board: Array<CharArray>, seen: Array<BooleanArray>) =
        (row in board.indices && col in board[row].indices && !seen[row][col])
}

fun main() {
    println(
        WordSearch().exist(
            arrayOf(
                charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'C', 'S'), charArrayOf('A', 'D', 'E', 'E')
            ), "ABCCED"
        )
    )
}