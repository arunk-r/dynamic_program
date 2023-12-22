package com.dynamic.program.arrays.medium

class SnakesAndLadders {
    fun snakesAndLadders(board: Array<IntArray>): Int {
        val n = board.size
        val N = n * n
        val flattenArray = flattenBoard(board)
        val graph = buildGraph(flattenArray)
        val q = ArrayDeque<Int>()
        q.add(1)
        val distance = IntArray(N+1){Int.MAX_VALUE}
        distance[1] = 0
        while (q.isNotEmpty()) {
            val node = q.removeFirst()
            graph[node]?.forEach { next ->
                if(distance[next] > distance[node]+1) {
                    distance[next] = distance[node]+1
                    q.addLast(next)
                }
            }
        }
        return if(distance[N] == Int.MAX_VALUE) -1 else distance[N]
    }

    private fun buildGraph(array: IntArray): HashMap<Int, MutableList<Int>> {
        val graph = HashMap<Int, MutableList<Int>>()
        for (cell in 1 .. array.lastIndex) {
            graph.putIfAbsent(cell, mutableListOf())
            for (dice in 1 .. 6) {
                val nextStep = cell + dice
                if(nextStep > array.lastIndex) continue
                if (array[nextStep] == -1) {
                    graph[cell]?.add(nextStep)
                } else {
                    graph[cell]?.add(array[nextStep])
                }
            }
        }
        return graph
    }


    private fun flattenBoard(board: Array<IntArray>): IntArray {
        val n = board.size
        val flatten = IntArray(n * n +1)
        for(i in board.indices) if(i%2 == n%2) board[i].reverse()
        board.reverse()
        for (r in board.indices) {
            for (c in 1 .. board[r].size) {
                flatten[n * r + c] = board[r][c-1]
            }
        }
        return flatten
    }
}

fun main() {
    val sal = SnakesAndLadders()
    println(sal.snakesAndLadders(arrayOf(intArrayOf(1, -1, -1), intArrayOf(9, -1, -1), intArrayOf(-1, 6, -1))))//1
    println(sal.snakesAndLadders(arrayOf(intArrayOf(1, 1, -1), intArrayOf(1, 1, 1), intArrayOf(-1, 1, 1))))//-1
    println(sal.snakesAndLadders(arrayOf(intArrayOf(-1, -1), intArrayOf(-1, 3))))//1
}
