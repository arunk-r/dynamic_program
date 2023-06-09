package com.dynamic.program.graphs.bfs.medium

/**
 * 529. Minesweeper - https://leetcode.com/problems/minesweeper/description/
 * Medium
 * 1.8K
 * 984
 * company
 * Uber
 * company
 * Amazon
 * company
 * Facebook
 * Let's play the minesweeper game (Wikipedia, online game)!
 *
 * You are given an m x n char matrix board representing the game board where:
 *
 * 'M' represents an unrevealed mine,
 * 'E' represents an unrevealed empty square,
 * 'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
 * digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
 * 'X' represents a revealed mine.
 * You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').
 *
 * Return the board after revealing this position according to the following rules:
 *
 * If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
 * If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 *
 *
 * Example 1:
 *
 *
 * Input: board = [['E','E','E','E','E'],['E','E','M','E','E'],['E','E','E','E','E'],['E','E','E','E','E']], click = [3,0]
 * Output: [['B','1','E','1','B'],['B','1','M','1','B'],['B','1','1','1','B'],['B','B','B','B','B']]
 * Example 2:
 *
 *
 * Input: board = [['B','1','E','1','B'],['B','1','M','1','B'],['B','1','1','1','B'],['B','B','B','B','B']], click = [1,2]
 * Output: [['B','1','E','1','B'],['B','1','X','1','B'],['B','1','1','1','B'],['B','B','B','B','B']]
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 50
 * board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
 * click.length == 2
 * 0 <= clickr < m
 * 0 <= clickc < n
 * board[clickr][clickc] is either 'M' or 'E'.
 */
class Minesweeper {
    fun updateBoard(board: Array<CharArray>, click: IntArray): Array<CharArray> {
        val (r, c) = click
        if (board[r][c] == 'M') {
            board[r][c] = 'X'
            return board
        } else {
            board[r][c] = 'B'
        }
        val seen = hashSetOf<Pair<Int, Int>>()
        seen.add(Pair(r, c))
        dfs(r, c, board, seen)
        return board
    }
    val dir = listOf(Pair(1,0), Pair(0,1), Pair(-1,0), Pair(0,-1), Pair(-1,-1), Pair(-1,1), Pair(1, -1), Pair(1,1))
    fun countMines(r: Int, c: Int, board: Array<CharArray>): Int {
        var count = 0
        dir.forEach{ (r1, c1) ->
            val nr = r + r1
            val nc = c + c1
            if (nr in board.indices && nc in board[nr].indices && board[nr][nc] == 'M') {
                count ++
            }
        }
        return count
    }

    fun dfs(r: Int, c: Int, board: Array<CharArray>, seen: HashSet<Pair<Int, Int>>) {
        val cnt = countMines(r, c, board)
        if (cnt > 0) {
            board[r][c] = '0' + cnt
        }
        dir.forEach{ (r1, c1) ->
            val nr = r + r1
            val nc = c + c1
            if (nr in board.indices && nc in board[nr].indices && board[nr][nc] != 'B' && board[nr][nc] != 'M' && !seen.contains(Pair(nr, nc))) {
                board[nr][nc] = 'B'
                seen.add(Pair(nr, nc))
                dfs(nr, nc, board, seen)
            }
        }
    }
}

fun main() {
    val input = arrayOf(
        charArrayOf('E', 'E', 'E', 'E', 'E'),
        charArrayOf('E', 'E', 'M', 'E', 'E'),
        charArrayOf('E', 'E', 'E', 'E', 'E'),
        charArrayOf('E', 'E', 'E', 'E', 'E')
    )

    Minesweeper().updateBoard(input, intArrayOf(3,0))
}
