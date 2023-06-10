package com.dynamic.program.linked_list.medium

/**
 * 582. Kill Process
Medium
Amazon
Microsoft
Bloomberg
You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.

Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).

When a process is killed, all of its children processes will also be killed.

Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed. You may return the answer in any order.



Example 1:


Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
Output: [5,10]
Explanation: The processes colored in red are the processes that should be killed.
Example 2:

Input: pid = [1], ppid = [0], kill = 1
Output: [1]


Constraints:

n == pid.length
n == ppid.length
1 <= n <= 5 * 104
1 <= pid[i] <= 5 * 104
0 <= ppid[i] <= 5 * 104
Only one process has no parent.
All the values of pid are unique.
kill is guaranteed to be in pid.
 */
class KillProcess {
    fun killProcess(pid: List<Int>, ppid: List<Int>, kill: Int): List<Int> {
        val map = hashMapOf<Int, MutableList<Int>>()
        for(i in pid.indices) {
            val p = pid[i]
            val pp = ppid[i]
            map.putIfAbsent(p, mutableListOf())
            map.putIfAbsent(pp, mutableListOf())
            map[pp]?.add(p)
        }
        val result = mutableListOf<Int>()
        val seen = hashSetOf<Int>()
        val q = ArrayDeque<Int>()
        q.add(kill)
        seen.add(kill)
        while(q.isNotEmpty()) {
            val k = q.removeFirst()
            result.add(k)
            map[k]?.forEach{ nei ->
                if (!seen.contains(nei)) {
                    seen.add(nei)
                    q.addLast(nei)
                }
            }
        }
        return result
    }

    fun killProcess1(pid: List<Int>, ppid: List<Int>, kill: Int): List<Int> {
        val map = hashMapOf<Int, MutableList<Int>>()

        for(i in pid.indices) {
            val p = pid[i]
            val pp = ppid[i]
            map.putIfAbsent(pp, mutableListOf())
            map[pp]!!.add(p)
        }

        val result = mutableListOf<Int>()
        result.add(kill)
        getChildren(kill, map, result)
        return result
    }

    private fun getChildren(kill: Int, map: HashMap<Int, MutableList<Int>>, result: MutableList<Int>) {
        if(map.containsKey(kill)) {
            for(p in map[kill]!!) {
                result.add(p)
                getChildren(p, map, result)
            }
        }
    }
}
