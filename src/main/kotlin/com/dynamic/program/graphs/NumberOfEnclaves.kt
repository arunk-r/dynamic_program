package com.dynamic.program.graphs

/**
 * Number of Enclaves
 */
class NumberOfEnclaves {
    private val dir = listOf(Pair(-1,0),Pair(0,-1),Pair(0,1),Pair(1,0))
    fun numEnclaves(grid: Array<IntArray>): Int {
        var finalCnt = 0
        val q = ArrayDeque<Pair<Int, Int>>()

        val m = grid.size
        val n = grid[0].size
        val seen = MutableList(m){BooleanArray(n)}

        for(r in 1 until grid.size -1) {
            for(c in 1 until grid[r].size - 1) {
                if(grid[r][c] == 1 && !seen[r][c]) {
                    var cnt = 0
                    var isValid = true
                    q.addLast(Pair(r,c))
                    seen[r][c] = true
                    while (q.isNotEmpty()) {
                        val cur = q.removeFirst()
                        val x = cur.first
                        val y = cur.second

                        if (x == 0 || x == m -1 || y == 0 || y == n -1 ) {
                            isValid = false
                        }
                        cnt++
                        dir.forEach{ p ->
                            val nr = x + p.first
                            val nc = y + p.second
                            if (valid(nr, nc, grid) && !seen[nr][nc]) {
                                seen[nr][nc] = true
                                q.addLast(Pair(nr,nc))
                            }
                        }
                    }
                    if (isValid) {
                        finalCnt += cnt
                    }
                }
            }
        }
        return finalCnt
    }

    private fun valid(r: Int, c: Int, grid: Array<IntArray>): Boolean =
        (r >=0 && r < grid.size && c >=0 && c < grid[r].size && grid[r][c] == 1)

}

fun main() {
    println(NumberOfEnclaves().numEnclaves(arrayOf(intArrayOf(0,1,1,0),intArrayOf(0,0,1,0),intArrayOf(0,0,1,0),intArrayOf(0,0,0,0))))
    println(NumberOfEnclaves().numEnclaves(arrayOf(intArrayOf(0,0,0,1,1,1,0,1,1,1,1,1,0,0,0),intArrayOf(1,1,1,1,0,0,0,1,1,0,0,0,1,1,1),intArrayOf(1,1,1,0,0,1,0,1,1,1,0,0,0,1,1),intArrayOf(1,1,0,1,0,1,1,0,0,0,1,1,0,1,0),intArrayOf(1,1,1,1,0,0,0,1,1,1,0,0,0,1,1),intArrayOf(1,0,1,1,0,0,1,1,1,1,1,1,0,0,0),intArrayOf(0,1,0,0,1,1,1,1,0,0,1,1,1,0,0),intArrayOf(0,0,1,0,0,0,0,1,1,0,0,1,0,0,0),intArrayOf(1,0,1,0,0,1,0,0,0,0,0,0,1,0,1),intArrayOf(1,1,1,0,1,0,1,0,1,1,1,0,0,1,0))))
}