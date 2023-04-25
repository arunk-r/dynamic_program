package com.dynamic.program.stacks_queues.medium

import java.util.*

/**
 * 735. Asteroid Collision
Medium

company
Amazon
company
TikTok
company
DoorDash
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.



Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.


Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
 */
class AsteroidCollision {
    fun asteroidCollision(asteroids: IntArray): IntArray {
        val s = Stack<Int>()

        for (asteroid in asteroids) {
            var push = true
            while (s.isNotEmpty() && s.peek() > 0 && asteroid < 0) {
                val peek = Math.abs(s.peek())
                val cur = Math.abs(asteroid)
                if (peek >= cur) {
                    push = false
                    if (peek == cur) {
                        s.pop()
                    }
                    break
                } else {
                    s.pop()
                }
            }
            if (push) {
                s.push(asteroid)
            }
        }


        if (s.isNotEmpty()) {
            val v = IntArray(s.size)
            var idx = s.size - 1
            while (s.isNotEmpty()) {
                v[idx--] = s.pop()
            }
            return v
        } else {
            return intArrayOf()
        }
    }
}

fun main() {
    println(AsteroidCollision().asteroidCollision(intArrayOf(10, 2, -5)).toList())
    println(AsteroidCollision().asteroidCollision(intArrayOf(8, -8)).toList())
    println(AsteroidCollision().asteroidCollision(intArrayOf(5, 10, -5)).toList())
    println(AsteroidCollision().asteroidCollision(intArrayOf(2, -5, 10, -5)).toList())
}
