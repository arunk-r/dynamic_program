package com.dynamic.program.trees.dfs.medium

/**
 * 1197. Minimum Knight Moves
 * Medium
 *
 * company
 * Indeed
 * company
 * Amazon
 * company
 * Google
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 * Example 2:
 *
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 *
 *
 * Constraints:
 *
 * -300 <= x, y <= 300
 * 0 <= |x| + |y| <= 300
 */
class MinimumKnightMoves {
    val dir = listOf(Pair(2, 1), Pair(2, -1), Pair(-2, 1), Pair(-2, -1), Pair(-1,2), Pair(1,2), Pair(-1,-2), Pair(1,-2))
    fun minKnightMoves(x: Int, y: Int): Int {
        val nx = Math.abs(x)
        val ny = Math.abs(y)
        if (nx == 1 && ny == 1) return 2
        val q = ArrayDeque<Pair<Int, Int>>()
        q.addLast(Pair(0, 0))
        var moves = 1
        val seen = hashSetOf<Pair<Int, Int>>()
        seen.add(Pair(0,0))
        while(q.isNotEmpty()) {
            val s = q.size
            for (i in 0 until s) {
                val (r,c) = q.removeFirst()
                if (r == nx && c == ny) return moves-1
                dir.forEach{ (r1, c1) ->
                    val nr = r + r1
                    val nc = c + c1
                    val p = Pair(nr, nc)
                    if (nr >= 0 && nc >= 0 && !seen.contains(p)) {
                        q.addLast(p)
                        seen.add(p)
                    }
                }
            }
            moves++
        }
        return -1
    }
}
