package com.dynamic.program.dp

/**
 * Best Time to Buy and Sell Stock with Transaction Fee
 */
class BestTimeToBuyAndSellStockWithTransactionFee {
    fun maxProfit(prices: IntArray, fee: Int): Int {
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