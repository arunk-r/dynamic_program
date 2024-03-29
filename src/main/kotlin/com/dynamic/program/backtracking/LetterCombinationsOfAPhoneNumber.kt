package com.dynamic.program.backtracking

/**
 * Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 *
 * Example 2:
 * Input: digits = ""
 * Output: []
 *
 *
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * Constraints:
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 *
 */
class LetterCombinationsOfAPhoneNumber {
    val result = mutableListOf<String>()
    fun letterCombinations(digits: String): List<String> {
        val map = hashMapOf<Char, MutableList<Char>>()
        map['2'] = mutableListOf('a', 'b', 'c')
        map['3'] = mutableListOf()
        map['4'] = mutableListOf()
        map['5'] = mutableListOf()
        map['6'] = mutableListOf()
        map['7'] = mutableListOf()
        map['8'] = mutableListOf()
        map['9'] = mutableListOf()
        finCombination(0, digits, StringBuffer(), map)
        return result
    }

    private fun finCombination(idx: Int, digits: String, cur: StringBuffer, map: HashMap<Char, MutableList<Char>>) {
        if(idx == digits.length) {
            if(cur.isNotEmpty()) {
                result.add(cur.toString())
            }
        } else {
            println(digits[idx])
            map[digits[idx]]?.forEach { c->
                cur.append(c)
                finCombination(idx+1, digits, cur, map)
                cur.deleteCharAt(cur.length-1)
            }
        }
    }


    fun letterCombinations1(digits: String): List<String> {
        val map = hashMapOf<Int, MutableList<Char>>()
        map[2] = mutableListOf('a','b','c')
        map[3] = mutableListOf('d','e','f')
        map[4] = mutableListOf('g','h','i')
        map[5] = mutableListOf('j','k','l')
        map[6] = mutableListOf('m','n','o')
        map[7] = mutableListOf('p','q','r','s')
        map[8] = mutableListOf('t','u','v')
        map[9] = mutableListOf('w','x','y','z')

        if (digits.isEmpty()) return emptyList()
        var result = mutableListOf<String>("")

        digits.forEach{ d ->
            result = helper(map[d - '0']!!, result)
        }
        return result
    }

    fun helper(chars: MutableList<Char>, curr: MutableList<String>) : MutableList<String> {
        val result = mutableListOf<String>()
        chars.forEach{ c ->
            curr.forEach{ s ->
                result.add("$s$c")
            }
        }
        return result
    }
}

fun main() {
    val l = LetterCombinationsOfAPhoneNumber()
    println(l.letterCombinations("23"))
}
