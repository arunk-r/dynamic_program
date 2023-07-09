package com.dynamic.program.greedy.medium

/**
 * https://leetcode.com/problems/task-scheduler/description/
 *
 * 621. Task Scheduler
 * Medium
 * 8.5K
 * 1.7K
 * company
 * Amazon
 * company
 * Microsoft
 * company
 * Google
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *
 * Constraints:
 *
 * 1 <= task.length <= 104
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 */
class TaskScheduler {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val frequency = IntArray(26)
        for(c in tasks) {
            frequency[c - 'A']++
        }
        frequency.sort()
        val max = frequency[25]
        var idleTime = (max - 1) * n
        for(i in frequency.size-2 downTo 0){
            idleTime -= minOf(max-1, frequency[i])
        }
        return maxOf(idleTime, 0) + tasks.size
    }

    fun leastInterval1(tasks: CharArray, n: Int): Int {
        val map = hashMapOf<Char, Int>()
        var max = 0
        for(c in tasks) {
            map.putIfAbsent(c, 0)
            map[c] = map[c]!!+1
            max = maxOf(max, map[c]!!)
        }
        var count = 0
        map.forEach{ (_, v) ->
            if(v == max) {
                count ++
            }
        }
        return maxOf(tasks.size, ((max - 1) * (n+1)) + count)
    }
}
