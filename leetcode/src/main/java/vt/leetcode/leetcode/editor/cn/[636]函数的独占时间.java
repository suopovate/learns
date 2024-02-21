package vt.leetcode.leetcode.editor.cn;

import java.util.List;
import java.util.Stack;

class ExclusiveTimeOfFunctions {
    public static void main(String[] args) {
        Solution solution = new ExclusiveTimeOfFunctions().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] exclusiveTime(int n, List<String> logs) {
            // n个函数 logs 是函数执行日志
            // Logs 是一个执行调用栈
            // 程序调用 是一个嵌套的 最内层 肯定是 end 和 start 配套
            // 所以 我们搞一个栈 将logs 出栈，如果是end就推进去，如果是start，就从end出栈一个
            Stack<String> tempStack = new Stack<>();
            // 结果
            int[] ans = new int[n];
            // 临时保存各个函数在计算时 内部子方法的总耗时
            int[] temp = new int[n];
            for (String log : logs) {
                if (log.contains("start")) {
                    tempStack.push(log);
                } else {
                    // 找到了一个方法的结束
                    String start = tempStack.pop();
                    // 计算当前函数的总耗时，并且将当前方法总耗时，暂时存到外部耗时去
                    int curTotalTime = computeTime(start, log);
                    if (!tempStack.isEmpty()) {
                        int parent = Integer.parseInt(tempStack.peek().split(":")[0]);
                        temp[parent] += curTotalTime;
                    }
                    // 去掉子函数的耗时
                    int curIndex = Integer.parseInt(start.split(":")[0]);
                    int curTime = curTotalTime - temp[curIndex];
                    ans[curIndex] += curTime;
                    // 这里为什么要置0呢？因为可能出现 s1 e1 s1 e1 这种情况
                    temp[curIndex] = 0;
                }
            }
            return ans;
        }
        private int computeTime(String start, String end) {
            return Integer.parseInt(end.split(":")[2]) - Integer.parseInt(start.split(":")[2]) + 1;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
