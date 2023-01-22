package com.dynamic.program.greedy

/**
 * Video Stitching
 *
 * You are given a series of video clips from a sporting event that lasted time seconds. These video clips can be overlapping with each other and have varying lengths.
 * Each video clip is described by an array clips where clips[i] = [starti, endi] indicates that the ith clip started at starti and ended at endi.
 *
 * We can cut these clips into segments freely.
 * For example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 * Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event [0, time]. If the task is impossible, return -1.
 *
 *
 *
 * Example 1:
 * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
 * Output: 3
 * Explanation: We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows:
 * We cut [1,9] into segments [1,2] + [2,8] + [8,9].
 * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
 *
 *
 * Example 2:
 * Input: clips = [[0,1],[1,2]], time = 5
 * Output: -1
 * Explanation: We cannot cover [0,5] with only [0,1] and [1,2].
 *
 *
 * Example 3:
 * Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
 * Output: 3
 * Explanation: We can take clips [0,4], [4,7], and [6,9].
 *
 *
 *
 * Constraints:
 * 1 <= clips.length <= 100
 * 0 <= start[i] <= end[i] <= 100
 * 1 <= time <= 100
 *
 */
class VideoStitching {
    fun videoStitching(clips: Array<IntArray>, time: Int): Int {
        clips.sortWith(kotlin.Comparator { x, y -> if (x[0] == y[0]) y[1] - x[1] else x[0] - y[0] })
        var cnt = 1
        var idx = 1
        var cT = clips[0][1]
        var cS = clips[0][0]
        if (cS != 0) return -1
        while (idx < clips.size && cT < time) {
            var newSrt = cS
            var newEnd = cT
            while (idx < clips.size) {
                val clp = clips[idx]
                if (clp[0] <= cT) {
                    newSrt = Math.max(clp[0], newSrt)
                    newEnd = Math.max(clp[1], newEnd)
                    idx++
                } else {
                    break
                }
            }
            if (cT == newEnd) return -1
            cS = newSrt
            cT = newEnd
            cnt++
        }
        if (cT < time) return -1
        return cnt
    }
}

fun main() {
    /*println(
        VideoStitching().videoStitching(
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(6, 8),
                intArrayOf(0, 2),
                intArrayOf(5, 6),
                intArrayOf(0, 4),
                intArrayOf(0, 3),
                intArrayOf(6, 7),
                intArrayOf(1, 3),
                intArrayOf(4, 7),
                intArrayOf(1, 4),
                intArrayOf(2, 5),
                intArrayOf(2, 6),
                intArrayOf(3, 4),
                intArrayOf(4, 5),
                intArrayOf(5, 7),
                intArrayOf(6, 9)
            ), 9
        )
    )*/

    //println(VideoStitching().videoStitching(arrayOf(intArrayOf(0, 4), intArrayOf(2, 8)), 5))
    //println(VideoStitching().videoStitching(arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)), 5))
    println(VideoStitching().videoStitching(arrayOf(intArrayOf(8,10),intArrayOf(17,39),intArrayOf(18,19),intArrayOf(8,16),intArrayOf(13,35),intArrayOf(33,39),intArrayOf(11,19),intArrayOf(18,35)), 20))
}