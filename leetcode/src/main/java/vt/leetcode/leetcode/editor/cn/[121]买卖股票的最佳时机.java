package vt.leetcode.leetcode.editor.cn;

class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStock().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            int maxProfit = 0, minPrice = -1;
            for (int price : prices) {
                if (minPrice != -1) {
                    if (price > minPrice)
                        maxProfit = Math.max(price - minPrice, maxProfit);
                    else
                        minPrice = Math.min(price, minPrice);
                } else {
                    minPrice = price;
                }
            }
            return maxProfit;
        }

        public int maxProfitViolent(int[] prices) {
            int maxPrice = 0;
            for (int i = 0; i < prices.length; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    if (prices[j] > prices[i]) {
                        maxPrice = Math.max(prices[j] - prices[i], maxPrice);
                    }
                }
            }
            return maxPrice;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
