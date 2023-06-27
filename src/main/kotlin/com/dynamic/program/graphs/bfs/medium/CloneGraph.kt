package com.dynamic.program.graphs.bfs.medium

/**
 * 133. Clone Graph
 * Medium
 * company
 * Facebook
 * company
 * Amazon
 * company
 * Apple
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * Test case format:
 *
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 *
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * Example 2:
 *
 *
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * Example 3:
 *
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 *
 *
 * Constraints:
 *
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 */
object CloneGraph {
    data class Node(val `val`: Int, val neighbors: MutableList<Node?> = mutableListOf())
    fun cloneGraph(node: Node?): Node? {
        if(node == null) return node

        val map = hashMapOf<Node, Node>()
        map[node] = Node(node.`val`)
        val q = ArrayDeque<Node>()
        q.addLast(node)
        while(q.isNotEmpty()) {
            val cur = q.removeFirst()
            for(nei in cur.neighbors) {
                if(nei != null && !map.containsKey(nei)) {
                    q.add(nei)
                    map[nei] = Node(nei.`val`)
                }
                map[cur]?.neighbors?.add(map[nei]!!)
            }
        }
        return map[node]!!
    }


    fun cloneGraph2(node: Node?): Node? {
        if (node == null) return node
        val map = hashMapOf<Node, Node>()
        val q = ArrayDeque<Node>()
        q.addLast(node)
        map[node] = Node(node.`val`)
        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            cur.neighbors.forEach { nei ->
                if (nei != null && map[nei] == null) {
                    map[nei] = Node(nei.`val`)
                    q.add(nei)
                }
                map[cur]!!.neighbors.add(map[nei])
            }
        }
        return map[node]
    }


    fun cloneGraph1(node: Node?): Node? {
        if (node == null) return node
        val graph = hashMapOf<Int, MutableSet<Int>>()
        val map = hashMapOf<Int, Node>()

        val q = ArrayDeque<Node>()
        q.addLast(node)
        val seen = hashSetOf<Int>()
        while (q.isNotEmpty()) {
            for (i in q.size - 1 downTo 0) {
                val n = q.removeFirst()
                graph.putIfAbsent(n.`val`, mutableSetOf())
                if (!map.containsKey(n.`val`)) {
                    map[n.`val`] = Node(n.`val`)
                }
                seen.add(n.`val`)
                n.neighbors.forEach { nn ->
                    if (nn != null) {
                        graph.putIfAbsent(nn.`val`, mutableSetOf())
                        graph[n.`val`]?.add(nn.`val`)
                        graph[nn.`val`]?.add(n.`val`)
                        if (!seen.contains(nn.`val`)) {
                            q.addLast(nn)
                        }
                    }
                }
            }
        }
        //println(map)
        //println(graph)
        return dfs(1, graph, hashMapOf<Int, MutableSet<Int>>(), map, hashSetOf(1))
    }

    fun dfs(
        n: Int,
        graph: HashMap<Int, MutableSet<Int>>,
        nGraph: HashMap<Int, MutableSet<Int>>,
        map: HashMap<Int, Node>,
        seen: HashSet<Int>
    ): Node {
        val node = map[n]!!
        nGraph.putIfAbsent(n, mutableSetOf())
        seen.add(n)
        graph[n]?.forEach { v ->
            val neb = map[v]!!
            nGraph.putIfAbsent(v, mutableSetOf())
            if (!nGraph[n]!!.contains(v)) {
                node.neighbors.add(neb)
                nGraph[n]?.add(v)
            }
            if (!seen.contains(v)) {
                nGraph.putIfAbsent(v, mutableSetOf())
                nGraph[v]?.add(n)
                neb.neighbors.add(node)
                dfs(v, graph, nGraph, map, seen)
            }
        }

        return node
    }
}
