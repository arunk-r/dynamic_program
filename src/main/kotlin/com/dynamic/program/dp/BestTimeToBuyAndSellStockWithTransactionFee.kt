package com.dynamic.program.dp

/**
 * Best Time to Buy and Sell Stock with Transaction Fee
 */
class BestTimeToBuyAndSellStockWithTransactionFee {
    fun maxProfit(prices: IntArray, fee: Int): Int {
        val m = prices.size
        val buy = IntArray(m+2)
        val sell = IntArray(m+2)
        buy[1] = -prices[0]

        for(i in 2 until m+2) {
            val p = prices[i-2]
            buy[i] = maxOf(buy[i-1], sell[i-1] - p)
            sell[i] = maxOf(sell[i-1], buy[i-1] + p - fee)
        }

        return maxOf(buy[m+1], sell[m+1])
    }

    fun maxProfit2(prices: IntArray, fee: Int): Int {
        var cash = 0
        var hold = -prices[0]

        for(i in 1 until prices.size) {
            cash = Math.max(cash, hold+prices[i] - fee)
            hold = Math.max(hold, cash - prices[i])
        }
        return cash
    }

    fun maxProfit1(prices: IntArray): Int {
        var cash = 0
        var hold = -prices[0]
        var previous = 0

        for(i in 1 until prices.size) {
            val cashTmp = cash
            cash = Math.max(cash, hold+prices[i])
            hold = Math.max(hold, previous - prices[i])
            previous = cashTmp
        }
        return cash
    }
}
