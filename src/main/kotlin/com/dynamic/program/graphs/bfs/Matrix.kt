package com.dynamic.program.graphs.bfs

/**
 * Matrix
 *
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 *
 * Example 2:
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 * Constraints:
 * m == mat.length
 * n == mat\[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * mat\[i]\[j] is either 0 or 1.
 * There is at least one 0 in mat.
 */
class Matrix {
    val dir = listOf(Pair(1,0), Pair(0,1),Pair(-1,0), Pair(0,-1))
    data class State(val r: Int, val c: Int, val d: Int)
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val seen = Array(mat.size){BooleanArray(mat[0].size)}
        val q = ArrayDeque<State>()
        for(r in mat.indices) {
            for (c in mat[r].indices) {
                if (mat[r][c] == 0) {
                    seen[r][c] = true
                    q.addLast(State(r,c,1))
                }
            }
        }

        while (q.isNotEmpty()) {
            val (r,c,d) = q.removeFirst()
            dir.forEach{ (r1, c1) ->
                val nr = r + r1
                val nc = c + c1
                if (valid(nr, nc, mat) && !seen[nr][nc]) {
                    seen[nr][nc] = true
                    q.addLast(State(nr,nc,d+1))
                    mat[nr][nc] = d
                }
            }
        }

        return mat
    }

    fun valid(r: Int, c: Int, mat: Array<IntArray>): Boolean =
        (r in mat.indices && c in mat[r].indices && mat[r][c] == 1)

    /*private val dir = mutableListOf(Pair(-1, 0), Pair(1, 0), Pair(0, 1), Pair(0, -1))
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {

        val queue = ArrayDeque<State>()
        val seen = MutableList(mat.size) { BooleanArray(mat[0].size) }
        for (r in mat.indices) {
            for (c in mat[r].indices) {
                if (mat[r][c] == 0) {
                    queue.addLast(State(r, c, 1))
                    seen[r][c] = true
                }
            }
        }

        while (queue.isNotEmpty()) {
            val n = queue.removeFirst()
            dir.forEach { d ->
                val nr = n.row + d.first
                val nc = n.col + d.second
                if (isValid(nr, nc, mat) && !seen[nr][nc]) {
                    seen[nr][nc] = true
                    queue.addLast(State(nr, nc, n.cnt + 1))
                    mat[nr][nc] = n.cnt
                }
            }
        }
        return mat
    }

    private fun isValid(r: Int, c: Int, mat: Array<IntArray>) =
            r >= 0 && c >= 0 && r < mat.size && c < mat[r].size && mat[r][c] == 1*/
}

fun main() {
    println(Matrix().updateMatrix(arrayOf(intArrayOf(0,0,0), intArrayOf(0,1,0), intArrayOf(1,1,1))))
}
