package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/design-tic-tac-toe/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 348. Design Tic-Tac-Toe
 * Medium
 * 2K
 * 114
 * company
 * Facebook
 * company
 * Amazon
 * company
 * Atlassian
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 *
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move, and the two players alternate in making moves. Return
 * 0 if there is no winner after the move,
 * 1 if player 1 is the winner after the move, or
 * 2 if player 2 is the winner after the move.
 *
 *
 * Example 1:
 *
 * Input
 * ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
 * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
 * Output
 * [null, 0, 0, 0, 0, 0, 0, 1]
 *
 * Explanation
 * TicTacToe ticTacToe = new TicTacToe(3);
 * Assume that player 1 is "X" and player 2 is "O" in the board.
 * ticTacToe.move(0, 0, 1); // return 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * ticTacToe.move(0, 2, 2); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * ticTacToe.move(2, 2, 1); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * ticTacToe.move(1, 1, 2); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * ticTacToe.move(2, 0, 1); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * ticTacToe.move(1, 0, 2); // return 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * player is 1 or 2.
 * 0 <= row, col < n
 * (row, col) are unique for each different call to move.
 * At most n2 calls will be made to move.
 */
class `DesignTic-Tac-Toe`(val n: Int) {
    val arr = Array(n){IntArray(n){-1}}
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

    fun ticTacToe(player: Int): Boolean {
        var r = 0
        var c = 0
        var cnt = 0
        while(c < n) {
            if(arr[r][c++] == player) {
                cnt++
            }
        }
        if (cnt == n) return true
        c--
        cnt = 0
        while(c >= 0) {
            if(arr[r][c] == player) {
                cnt++
            }
            r++
            c--
        }
        r--
        c++
        if (cnt == n) return true
        cnt = 0
        while(c < n) {
            if(arr[r][c++] == player) {
                cnt++
            }
        }
        c--
        if (cnt == n) return true
        cnt = 0
        while(c >= 0) {
            if(arr[r][c] == player) {
                cnt++
            }
            r--
            c--
        }
        return cnt == n
    }

}
