package com.dynamic.program.dp.hard

/**
 * https://leetcode.com/problems/smallest-sufficient-team/description/
 *
 * 1125. Smallest Sufficient Team
 * Hard
 * 2.1K
 * 52
 * company
 * Amazon
 * In a project, you have a list of required skills req_skills, and a list of people. The ith person people[i] contains a list of skills that the person has.
 *
 * Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill. We can represent these teams by the index of each person.
 *
 * For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 * Return any sufficient team of the smallest possible size, represented by the index of each person. You may return the answer in any order.
 *
 * It is guaranteed an answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
 * Output: [0,2]
 * Example 2:
 *
 * Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 1 <= req_skills.length <= 16
 * 1 <= req_skills[i].length <= 16
 * req_skills[i] consists of lowercase English letters.
 * All the strings of req_skills are unique.
 * 1 <= people.length <= 60
 * 0 <= people[i].length <= 16
 * 1 <= people[i][j].length <= 16
 * people[i][j] consists of lowercase English letters.
 * All the strings of people[i] are unique.
 * Every skill in people[i] is a skill in req_skills.
 * It is guaranteed a sufficient team exists.
 */
class SmallestSufficientTeam {
    var ans = intArrayOf()
    var min = 0
    fun smallestSufficientTeam(req_skills: Array<String>, people: List<List<String>>): IntArray {
        val map = HashMap<String, MutableList<Int>>()
        val sets = mutableListOf<HashSet<String>>()
        people.forEach {
            sets.add(it.toHashSet())
        }
        req_skills.forEach { skill ->
            map.putIfAbsent(skill, mutableListOf())
            sets.forEachIndexed { index, set ->
                if (set.contains(skill)) {
                    map[skill]?.add(index)
                }
            }
            map[skill]?.sortWith(kotlin.Comparator { x, y -> sets[x].size - sets[y].size })
        }
        min = sets.size + 1
        solve(0, hashSetOf(), 0, req_skills, ArrayDeque(), sets, map)
        return ans
    }

    private fun solve(i: Int, bucket: HashSet<String>, current: Int, req_skills: Array<String>, queue: ArrayDeque<Int>, listSet: MutableList<HashSet<String>>, map: HashMap<String, MutableList<Int>>) {
        if (i == req_skills.size || current >= min) {
            if (current < min) {
                min = current
                ans = queue.toIntArray()
            }
        } else if (bucket.contains(req_skills[i])) {
            solve(i + 1, bucket, current, req_skills, queue, listSet, map)
        } else {
            map[req_skills[i]]?.forEach { skill ->
                queue.add(skill)
                val newBucket: HashSet<String> = bucket.toHashSet()
                newBucket.addAll(listSet[skill])
                solve(i + 1, newBucket, current + 1, req_skills, queue, listSet, map)
                queue.removeLast()
            }
        }
    }

    fun smallestRange(nums: List<List<Int>>): IntArray? {
        var minX = 0
        var minY = Int.MAX_VALUE
        val next = IntArray(nums.size)
        var flag = true
        var i = 0
        while (i < nums.size && flag) {
            var j = 0
            while (j < nums[i].size && flag) {
                var minI = 0
                var maxI = 0
                for (k in nums.indices) {
                    if (nums[minI][next[minI]] > nums[k][next[k]]) minI = k
                    if (nums[maxI][next[maxI]] < nums[k][next[k]]) maxI = k
                }
                if (minY - minX > nums[maxI][next[maxI]] - nums[minI][next[minI]]) {
                    minY = nums[maxI][next[maxI]]
                    minX = nums[minI][next[minI]]
                }
                next[minI]++
                if (next[minI] == nums[minI].size) {
                    flag = false
                }
                j++
            }
            i++
        }
        return intArrayOf(minX, minY)
    }
}

fun main() {
    println(SmallestSufficientTeam().smallestSufficientTeam(arrayOf("java", "nodejs", "reactjs"), listOf(
            listOf("java"), listOf("nodejs"), listOf("nodejs", "reactjs")
    )).toList())
}
