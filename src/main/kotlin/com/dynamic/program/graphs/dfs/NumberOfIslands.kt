package com.dynamic.program.graphs.dfs

/**
 * Number of Islands
 * Given an m x n 2D binary grid which represents a map of 1 (land) and 0 (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 */
class NumberOfIslands {
    fun numIslands3(grid: Array<CharArray>): Int {
        var count = 0
        val seen = hashSetOf<Pair<Int, Int>>()
        for(r in grid.indices) {
            for (c in grid[r].indices) {
                if ( grid[r][c] == '1' && !seen.contains(Pair(r,c))) {
                    bfs(r,c,grid,seen)
                    count++
                }
            }
        }
        return count
    }

    val dir = listOf(Pair(1,0), Pair(0,1), Pair(-1,0), Pair(0,-1))

    fun bfs(r: Int, c: Int, grid: Array<CharArray>, seen: HashSet<Pair<Int, Int>>) {
        val q = ArrayDeque<Pair<Int, Int>>()
        q.add(Pair(r,c))
        val seen = hashSetOf<Pair<Int, Int>>()
        seen.add(Pair(r,c))
        while(q.isNotEmpty()) {
            for(i in q.size-1 downTo 0) {
                val (r, c) = q.removeFirst()
                dir.forEach { (r1, c1) ->
                    val nr = r + r1
                    val nc = c + c1
                    if (nr in grid.indices && nc in grid[nr].indices && grid[nr][nc] == '1' && !seen.contains(Pair(nr,nc))) {
                        val p = Pair(nr,nc)
                        seen.add(p)
                        q.addLast(p)
                    }
                }
            }
        }
        println(seen)
    }

    private val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
    private val visited = mutableSetOf<String>()

    fun numIslands2(grid: Array<CharArray>): Int {
        var ans = 0
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                val str = "$row,$col"
                if (grid[row][col] == '1' && !visited.contains(str)) {
                    bfs(row, col, grid)
                    ans++
                }
            }
        }
        return ans
    }

    private fun isValid(row: Int, col: Int, grid: Array<CharArray>) =
            row >= 0 && col >= 0 && row < grid.size && col < grid[row].size && grid[row][col] == '1'

    private fun bfs(row: Int, col: Int, grid: Array<CharArray>) {
        val str = "$row,$col"
        if (!visited.contains(str)) {
            visited.add(str)
            directions.forEach { dir ->
                val newRow = row + dir.first
                val newCol = col + dir.second
                val newStr = "$newRow,$newCol"
                if (isValid(newRow, newCol, grid) && !visited.contains(newStr)) {
                    bfs(newRow, newCol, grid)
                }
            }
        }
    }


    /*private val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
    private val seen = HashSet<String>()

    fun numberOfIslands(grid: List<List<Int>>): Int {
        var ans = 0
        for (row in grid.indices) {
            for (col in grid[0].indices) {
                val str = getUniqueGridLoc(row, col)
                if (grid[row][col] == 1 && !seen.contains(str)) {
                    ans++
                    seen.add(str)
                    dfs(row, col, grid)
                }
            }
        }
        //println(seen)
        return ans
    }

    private fun isValid(row: Int, col: Int, grid: List<List<Int>>): Boolean {
        return row >= 0 && col >= 0 && row < grid.size && col < grid[0].size && grid[row][col] == 1
    }

    private fun getUniqueGridLoc(row: Int, col: Int) = '$row,$col'

    private fun dfs(row: Int, col: Int, grid: List<List<Int>>) {
        directions.forEach { pair ->
            val nextRow = row + pair.first
            val nextCol = col + pair.second
            val str = getUniqueGridLoc(nextRow, nextCol)
            if (isValid(nextRow, nextCol, grid) && !seen.contains(str)) {
                seen.add(str)
                dfs(nextRow, nextCol, grid)
            }
        }
    }*/

    fun numIslands(grid: Array<CharArray>): Int {
        val graph = hashMapOf<Int, MutableSet<Int>>()
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (grid[row][col] == '1') {
                    graph.putIfAbsent(row, mutableSetOf())
                    graph.putIfAbsent(col, mutableSetOf())
                    graph[row]?.add(col)
                    graph[col]?.add(row)
                }
            }
        }

        val visited = mutableSetOf<Int>()
        var ans = 0
        graph.forEach { (k, v) ->
            if (!visited.contains(k)) {
                dfs(k, graph, visited)
                ans++
            }
        }
        return ans
    }

    fun dfs(current: Int, graph: Map<Int, Set<Int>>, visited: MutableSet<Int>) {
        if (!visited.contains(current)) {
            visited.add(current)
            graph[current]?.forEach { neighbor ->
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, graph, visited)
                }
            }
        }
    }
}

fun main() {
    /* val island = listOf(
             listOf(1, 1, 0, 0, 0, 1),
             listOf(0, 1, 0, 0, 0, 0),
             listOf(0, 1, 1, 0, 1, 1),
             listOf(0, 0, 0, 0, 0, 1),
             listOf(1, 1, 1, 1, 0, 1),
             listOf(1, 1, 1, 1, 0, 1)
     )
     println(NumberOfIslands().numIslands(island))*/

    println(NumberOfIslands().numIslands2(arrayOf(charArrayOf('1', '0', '1', '1', '0', '1', '1'))))
}
