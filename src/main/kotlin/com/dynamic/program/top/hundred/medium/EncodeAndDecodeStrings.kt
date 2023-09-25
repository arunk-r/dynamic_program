package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/encode-and-decode-strings/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 271. Encode and Decode Strings
 * Medium
 * 1.3K
 * 362
 * company
 * Amazon
 * company
 * Facebook
 * company
 * Google
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 *
 * string encode(vector<string> strs) {
 *   // ... your code
 *   return encoded_string;
 * }
 * Machine 2 (receiver) has the function:
 * vector<string> decode(string s) {
 *   //... your code
 *   return strs;
 * }
 * So Machine 1 does:
 *
 * string encoded_string = encode(strs);
 * and Machine 2 does:
 *
 * vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 *
 * Implement the encode and decode methods.
 *
 * You are not allowed to solve the problem using any serialize methods (such as eval).
 *
 *
 *
 * Example 1:
 *
 * Input: dummy_input = ["Hello","World"]
 * Output: ["Hello","World"]
 * Explanation:
 * Machine 1:
 * Codec encoder = new Codec();
 * String msg = encoder.encode(strs);
 * Machine 1 ---msg---> Machine 2
 *
 * Machine 2:
 * Codec decoder = new Codec();
 * String[] strs = decoder.decode(msg);
 * Example 2:
 *
 * Input: dummy_input = [""]
 * Output: [""]
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] contains any possible characters out of 256 valid ASCII characters.
 *
 *
 * Follow up: Could you write a generalized algorithm to work on any possible set of characters?
 */
class EncodeAndDecodeStrings {
    // Encodes a list of strings to a single string.
    fun encode(strs: List<String>): String {
        //println(strs.toMutableList())
        val buf = StringBuffer()
        for(s in strs) {
            if (s.isEmpty()) {
                buf.append("<")
            }else {
                for(c in s) {
                    buf.append(c.toInt())
                    buf.append("#")
                }
            }
            buf.append("|")
        }

        return buf.toString()
    }

    // Decodes a single string to a list of strings.
    fun decode(s: String): List<String> {
        //println(s)
        val arr = s.split("|")
        val lst = mutableListOf<String>()
        for(str in arr) {
            if (str.isNotEmpty()) {
                val buf = StringBuffer()
                if (str == "<") {
                    buf.append("")
                } else {
                    //println(str)
                    val chars = str.split("#")
                    for(char in chars) {
                        if (char.isNotEmpty()) {
                            buf.append(char.toInt().toChar())
                        }
                    }
                }
                lst.add(buf.toString())
            }
        }
        return lst
    }
}
