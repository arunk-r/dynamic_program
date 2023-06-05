package com.dynamic.program.graphs.bfs

/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is
 * defined as one single character changed in the gene string.
 *
 *
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 * Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate
 * from startGene to endGene. If there is no such a mutation, return -1.
 *
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 *
 * Example 1:
 * Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
 * Output: 1
 *
 * Example 2:
 * Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * Output: 2
 *
 * Constraints:
 * 0 <= bank.length <= 10
 * startGene.length == endGene.length == bank\[i].length == 8
 * startGene, endGene, and bank\[i] consist of only the characters ['A', 'C', 'G', 'T'].
 */
class MinimumGeneticMutation {
    fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
        val q = ArrayDeque<String>()
        val set = hashSetOf<String>()
        val seen = hashSetOf<String>()
        q.addLast(startGene)
        seen.add(startGene)
        set.addAll(bank)

        var count = 0
        val choices = listOf('A', 'C', 'G', 'T')
        while(q.isNotEmpty()) {
            for(i in q.size downTo 1) {
                val cur = q.removeFirst()
                if (endGene == cur) return count
                for(i in cur.indices) {
                    choices.forEach{ c ->
                        val newSeq = "${cur.substring(0,i)}$c${cur.substring(i+1)}"
                        if (set.contains(newSeq) && !seen.contains(newSeq)) {
                            q.addLast(newSeq)
                            seen.add(newSeq)
                        }
                    }
                }
            }
            count++
        }

        return -1
    }

    fun minMutation1(startGene: String, endGene: String, bank: Array<String>): Int {
        var cnt = 0
        if(bank.isEmpty() || !bank.contains(endGene)) return -1

        val q = ArrayDeque<String>()
        val seen = hashSetOf<String>()

        q.addLast(startGene)
        seen.add(startGene)

        while(q.isNotEmpty()) {
            val size = q.size
            for(i in 0 until size) {
                val cur = q.removeFirst()
                if(cur == endGene) return cnt

                for(j in cur.indices) {
                    listOf('A', 'C', 'G', 'T').forEach{ c ->
                        val newS = "${cur.substring(0,j)}$c${cur.substring(j+1)}"
                        if(!seen.contains(newS) && bank.contains(newS)) {
                            q.addLast(newS)
                            seen.add(newS)
                        }
                    }
                }
            }
            cnt++
        }

        return -1
    }
}
