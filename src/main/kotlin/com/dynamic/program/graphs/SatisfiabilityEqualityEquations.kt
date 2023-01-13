package com.dynamic.program.graphs

class SatisfiabilityEqualityEquations {
    fun equationsPossible(equations: Array<String>): Boolean {
        val graph = hashMapOf<Int, MutableList<Int>>()
        val notEqlLst = mutableListOf<String>()
        equations.forEach{ eq ->
            val a = eq[0] - 'a'
            val b = eq[3] - 'a'
            if (eq[1] == '=') {
                graph.putIfAbsent(a, mutableListOf())
                graph.putIfAbsent(b, mutableListOf())

                graph[a]?.add(b)
                graph[b]?.add(a)
            } else {
                notEqlLst.add(eq)
            }
        }
        val color = MutableList(26){-1}
        for (i in color.indices) {
            dfs(i,i, color, graph)
        }
        notEqlLst.forEach{ eq ->
            val a = eq[0] - 'a'
            val b = eq[3] - 'a'
            if (color[a] == color[b]) return false
        }

        return true
    }

    fun dfs(node: Int, clr: Int, color: MutableList<Int>, graph: Map<Int, List<Int>>) {
        if( color[ node ] == -1) {
            color[node] = clr
            graph[node]?.forEach { nei ->
                dfs(nei, clr, color, graph)
            }
        }
    }
}

fun main() {
    //println(SatisfiabilityEqualityEquations().equationsPossible(arrayOf("a==b","b!=a")))
    println(SatisfiabilityEqualityEquations().equationsPossible(arrayOf("c==c","b==d","x!=z")))
}