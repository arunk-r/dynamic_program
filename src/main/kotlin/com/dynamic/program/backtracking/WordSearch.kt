package com.dynamic.program.backtracking

import java.util.Arrays

class WordSearch {
    val dir = listOf(Pair(1,0), Pair(0, 1), Pair(-1,0), Pair(0, -1))
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val seen = hashSetOf<Pair<Int, Int>>()
        for(r in board.indices) {
            for (c in board[r].indices) {
                if( board[r][c] == word[0]) {
                    seen.add(Pair(r, c))
                    if(exist(r, c, 1, board, word, seen)) {
                        return true
                    }
                    seen.remove(Pair(r, c))
                }
            }
        }
        return false
    }

    fun exist(r: Int, c: Int, idx: Int, board: Array<CharArray>, word: String, seen: HashSet<Pair<Int, Int>>): Boolean {
        seen.clone()
        if(idx == word.length) return true
        var p = Pair(r, c)
        dir.forEach{ (r1, c1) ->
            val nr = r + r1
            val nc = c + c1
            p = Pair(nr, nc)
            if(!seen.contains(p) && nr in board.indices && nc in board[nr].indices && board[nr][nc] == word[idx]) {
                seen.add(p)
                if(exist(r, c, idx+1, board, word, seen)) {
                    return true
                }
                seen.remove(p)
            }
        }
        return false
    }
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
