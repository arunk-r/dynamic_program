package com.dynamic.program.binary.search

/**
 * Escape the Spreading Fire
 */
class EscapeTheSpreadingFire {
    fun maximumMinutes(grid: Array<IntArray>): Int {
        val fire = mutableListOf<Pair<Int, Int>>()
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 1) {
                    fire.add(Pair(r, c))
                }
            }
        }
        val person = mutableListOf<Pair<Int, Int>>(Pair(0, 0))
        val (fireLast, fireLeftLast, fireTopLast) = calculateDistance(grid, fire)
        val (personLast, personLeftLast, personTopLast) = calculateDistance(grid, person)

        val diff = fireLast - personLast
        return if (fireLast == 0 && personLast != 0) 1000000000
        else if (fireLast != 0 && personLast == 0) -1
        else if (diff >= 0) {
            val d = if (fireTopLast - personTopLast <= diff && fireLeftLast - personLeftLast <= diff) {
                1
            } else 0
            diff - d
        } else -1
    }

    fun calculateDistance(grid: Array<IntArray>, start: MutableList<Pair<Int, Int>>): Triple<Int, Int, Int> {
        val m = grid.size
        val n = grid[0].size
        val distance = Array(m) { IntArray(n) { 0 } }
        val visited = Array(m) { BooleanArray(n) }

        val queue = ArrayDeque<Pair<Int, Int>>()
        start.forEach { s ->
            queue.addLast(s)
            visited[s.first][s.second] = true
        }

        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val (r, c) = queue.removeFirst()
                listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0)).forEach { (r1, c1) ->
                    val nr = r + r1
                    val nc = c + c1
                    if (nr in 0 until m && nc in 0 until n && grid[nr][nc] == 0 && !visited[nr][nc]) {
                        queue.addLast(Pair(nr, nc))
                        distance[nr][nc] = distance[r][c] + 1
                        visited[nr][nc] = true
                    }
                }
            }
        }
        return Triple(distance[m - 1][n - 1], distance[m - 1][n - 2], distance[m - 2][n - 1])
    }
/*
    fun maximumMinutes(grid: Array<IntArray>): Int {
        val fire = mutableListOf<Pair<Int, Int>>()
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 1) {
                    fire.add(Pair(r, c))
                }
            }
        }
        var lb = 0
        var ub = (grid.size - 1) * (grid[0].size - 1)
        while (lb < ub) {
            val mid = lb + (ub - lb) / 2
            val s = spread(mid, grid, fire)
            if (s) lb = mid + 1
            else ub = mid - 1
        }
        return when (lb) {
            0 -> -1
            (grid.size - 1) * (grid[0].size - 1) -> 1000000000
            else -> lb - 1
        }
    }

    private fun spread(mid: Int, grid: Array<IntArray>, fire: MutableList<Pair<Int, Int>>): Boolean {
        val m = grid.size
        val n = grid[0].size
        var move = mid
        val nGrid = clone(grid)

        val fireQ = ArrayDeque<Pair<Int, Int>>()
        fireQ.addAll(fire)
        while (fireQ.isNotEmpty() && move > 0) {
            if (move(fireQ, m, n, nGrid)) return false
            move--
        }

        val person = ArrayDeque<Pair<Int, Int>>()
        person.addLast(Pair(0, 0))
        while (person.isNotEmpty()) {
            val lastFire = move(fireQ, m, n, nGrid)
            if (move(person, m, n, nGrid)) return true
            if (lastFire) return false
        }
        return false
    }

    private fun move(queue: ArrayDeque<Pair<Int, Int>>, m: Int, n: Int, grid: Array<IntArray>): Boolean {
        val size = queue.size
        for (i in 0 until size) {
            val (r, c) = queue.removeFirst()
            listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0)).forEach { (r1, c1) ->
                val nr = r + r1
                val nc = c + c1
                if (r == m - 1 && c == n - 1) return true
                if (nr in 0 until m && nc in 0 until n && grid[nr][nc] == 0) {
                    queue.addLast(Pair(nr, nc))
                    grid[nr][nc] = -1
                }
            }
        }
        return false
    }

    private fun clone(grid: Array<IntArray>): Array<IntArray> {
        val clone = Array(grid.size) { IntArray(grid[0].size) }
        for (r in grid.indices) {
            clone[r] = grid[r].copyOf()
        }
        return clone
    }*/
}

fun main() {
    println(
        EscapeTheSpreadingFire().maximumMinutes(
            arrayOf(
                intArrayOf(0, 2, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 2, 2, 1, 0),
                intArrayOf(0, 2, 0, 0, 1, 2, 0),
                intArrayOf(0, 0, 2, 2, 2, 0, 2),
                intArrayOf(0, 0, 0, 0, 0, 0, 0)
            )
        )
    )/*
    println(
        EscapeTheSpreadingFire().maximumMinutes(
            arrayOf(
                intArrayOf(0,2,1,1,0),
                intArrayOf(1,2,0,0,1),
                intArrayOf(2,2,1,1,0)
            )
        )
    )
    println(
        EscapeTheSpreadingFire().maximumMinutes(
            arrayOf(
                intArrayOf(0, 2, 0, 0, 1),
                intArrayOf(0, 2, 0, 2, 2),
                intArrayOf(0, 2, 0, 0, 0),
                intArrayOf(0, 0, 2, 2, 0),
                intArrayOf(0, 0, 0, 0, 0)
            )
        )
    )
    println(
        EscapeTheSpreadingFire().maximumMinutes(
            arrayOf(
                intArrayOf(0, 2, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 2, 2, 1, 0),
                intArrayOf(0, 2, 0, 0, 1, 2, 0),
                intArrayOf(0, 0, 2, 2, 2, 0, 2),
                intArrayOf(0, 0, 0, 0, 0, 0, 0)
            )
        )
    )
    println(
        EscapeTheSpreadingFire().maximumMinutes(
            arrayOf(
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 1, 2, 0),
                intArrayOf(0, 2, 0, 0)
            )
        )
    )
    println(
        EscapeTheSpreadingFire().maximumMinutes(
            arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(2, 2, 0),
                intArrayOf(1, 2, 0)
            )
        )
    )*/
}