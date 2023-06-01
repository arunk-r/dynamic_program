package com.dynamic.program.strings_arrays.medium

/**
 * 187. Repeated DNA Sequences
 * Medium
 *
 * company
 * Amazon
 * company
 * LinkedIn
 * company
 * Google
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 *
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either 'A', 'C', 'G', or 'T'.
 */
class RepeatedDNASequences {
    fun findRepeatedDnaSequences(s: String): List<String> {
        val set = hashSetOf<String>()
        val result = mutableSetOf<String>()
        for(i in 0 until s.length - 10+1) {
            val ss = s.substring(i, i+10)
            if (set.contains(ss)) {
                result.add(ss)
            }
            set.add(ss)
        }
        return result.toList()
    }
}
