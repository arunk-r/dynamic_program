package com.dynamic.program.design.hard.tree.heap

import java.util.PriorityQueue
import java.util.TreeSet

/**
 * https://leetcode.com/problems/find-median-from-data-stream/description/
 *
 * 295. Find Median from Data Stream
 * Hard
 * 10.6K
 * 209
 * company
 * Amazon
 * company
 * Uber
 * IXL
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 * Example 1:
 *
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 *
 * Constraints:
 *
 * -105 <= num <= 105
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 104 calls will be made to addNum and findMedian.
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
class FindMedianFromDataStream {
    data class Data(val v: Int, val t: Int)
    private var time:Int = 0
    private val set = TreeSet<Data>{x,y -> if (x.v != y.v)x.v - y.v else x.t - y.t}
    private var low: Data? = null
    private var high: Data? = null
    fun addNum(num: Int) {
        val d = Data(num, time++)
        set.add(d)
        if(time == 1) {
            low = set.first()
            high = set.first()
            return
        }
        if(low != null && high != null) {
            if(low == high) {
                if(compare(d,low!!) < 0) {
                    high = low
                    low = set.lower(low)
                } else {
                    low = high
                    high = set.higher(high)
                }
                return
            }
            if(low != high) {
                if( compare(d,low!!) < 0) {
                    high = low
                } else if(compare(d,high!!) > 0) {
                    low = high
                } else {
                    low = d
                    high = d
                }
            }
        }
    }

    fun findMedian(): Double {
        if(low == null || high == null) return 0.0
        return (low!!.v + high!!.v) / 2.0
    }

    private fun compare(x: Data, y: Data): Int {
        return if (x.v != y.v) x.v - y.v else x.t - y.t
    }
}

class MedianFinder() {
    val min = PriorityQueue<Int>{x,y -> x-y}
    val max = PriorityQueue<Int>{x,y -> y-x}
    fun addNum(num: Int) {
        max.add(num)
        min.add(max.remove())
        if(min.size > max.size) {
            max.add(min.remove())
        }
    }

    fun findMedian(): Double {
        return if(max.size > min.size) {
            max.peek() * 1.0
        } else {
            (min.peek() + max.peek()) / 2.0
        }
    }

}


fun main() {
    val fm = FindMedianFromDataStream()
    fm.addNum(1)
    println(fm.findMedian())
    fm.addNum(2)
    println(fm.findMedian())
    fm.addNum(3)
    println(fm.findMedian())
    fm.addNum(4)
    println(fm.findMedian())
    fm.addNum(5)
    println(fm.findMedian())


    val fm1 = MedianFinder()
    fm1.addNum(1)
    println(fm1.findMedian())
    fm1.addNum(2)
    println(fm1.findMedian())
    fm1.addNum(3)
    println(fm1.findMedian())
    fm1.addNum(4)
    println(fm1.findMedian())
    fm1.addNum(5)
    println(fm1.findMedian())
}
