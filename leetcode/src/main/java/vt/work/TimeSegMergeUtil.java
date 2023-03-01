package vt.work;

import cn.hutool.core.lang.Tuple;

import java.util.*;

/**
 * * @author 曾碹
 */
public class TimeSegMergeUtil {

    /**
     * 合并时间费用desc列表
     * 合并两个时间段费用描述列表为同一个，调用时需保证传进来的是经过检测的合法费用描述内容
     *
     * @param feeDescList1 0: startHour(HHmm) 1: endHour(HHmm) 2: 价格
     * @param feeDescList2 0: startHour(HHmm) 1: endHour(HHmm) 2: 价格
     * @return {@link List}<{@link Tuple}> 0: startHour(HHmm) 1: endHour(HHmm) 2: feeDesc1价格 3: feeDesc2价格
     */
    public static List<Tuple> mergeTimeFeeDescList(List<Tuple> feeDescList1, List<Tuple> feeDescList2) {
        // 构建两个时间段的栈，00点对应的时间段在栈顶，24点对应的时间段在栈底
        Stack<Tuple> stack1 = new Stack<>(), stack2 = new Stack<>();
        // 列表按照时间段的开始时间排序，按时间越大的先入栈
        // 栈的最终效果：0000 - x1x1, ... , x2x2 - 2400
        feeDescList1.stream().sorted((o1, o2) -> Integer.compare(o2.get(0), o1.get(0))).forEach(stack1::push);
        feeDescList2.stream().sorted((o1, o2) -> Integer.compare(o2.get(0), o1.get(0))).forEach(stack2::push);
        // 合并后的结果
        List<Tuple> mergeResult = new ArrayList<>();
        // 遍历两个栈，直到处理完所有元素
        // 两个栈，如果为空就肯定会同时空，因为想要消除一个元素，必须要另外一边同时也消除
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            // 栈顶元素出栈，这里有个特点，两个时间段的startHourInt肯定是一样的。
            Tuple feeDesc1 = stack1.pop(), feeDesc2 = stack2.pop();
            int endHourInt1 = feeDesc1.get(1), endHourInt2 = feeDesc2.get(1);
            // 两个时间段一致，那就直接合并
            if (endHourInt1 == endHourInt2) {
                mergeResult.add(new Tuple(feeDesc1.get(0), endHourInt1, feeDesc1.get(2), feeDesc2.get(2)));
            } else {
                // 找出时间范围更小的那个段
                Tuple minEndHourFeeDesc = endHourInt1 < endHourInt2 ? feeDesc1 : feeDesc2;
                mergeResult.add(new Tuple(minEndHourFeeDesc.get(0), minEndHourFeeDesc.get(1), feeDesc1.get(2), feeDesc2.get(2)));
                // 把时间范围更大的段，进行分割，分割后的小段，回栈
                if (feeDesc1 == minEndHourFeeDesc) {
                    // 2 更大
                    stack2.push(new Tuple(minEndHourFeeDesc.get(1), feeDesc2.get(1), feeDesc2.get(2)));
                } else {
                    // 1 更大
                    stack1.push(new Tuple(minEndHourFeeDesc.get(1), feeDesc1.get(1), feeDesc1.get(2)));
                }
            }
        }
        return mergeResult;
    }


    /**
     * 合并时间费用desc列表
     * 合并两个时间段费用描述列表为同一个，调用时需保证传进来的是经过检测的合法费用描述内容
     *
     * @param feeDescList1 0: startHour(HHmm) 1: endHour(HHmm) 2: 价格
     * @param feeDescList2 0: startHour(HHmm) 1: endHour(HHmm) 2: 价格
     * @return {@link List}<{@link Tuple}> 0: startHour(HHmm) 1: endHour(HHmm) 2: feeDesc1价格 3: feeDesc2价格
     */
    public static List<Tuple> mergeTimeFeeDescList2(List<Tuple> feeDescList1, List<Tuple> feeDescList2) {
        List<Tuple> mergeResult = new ArrayList<>();
        int i1 = 0, i2 = 0;
        while (i1 < feeDescList1.size() && i2 < feeDescList2.size()) {
            Tuple fee1 = feeDescList1.get(i1);
            Tuple fee2 = feeDescList2.get(i2);
            int startHourInt1 = fee1.get(0), startHourInt2 = fee2.get(0);
            int endHourInt1 = fee1.get(1), endHourInt2 = fee2.get(1);
            int resultStartHour = 0, resultEndHour = 0;
            // 开始时间计算
            if (startHourInt1 == startHourInt2) {
                resultStartHour = startHourInt1;
            } else {
                resultStartHour = Math.max(startHourInt1, startHourInt2);
            }
            // 结束时间相同，一起消，否则谁短消谁
            if (endHourInt1 == endHourInt2) {
                // 直接合并
                resultEndHour = endHourInt1;
                i1++;
                i2++;
                // 否则取交叉部分
            } else if (endHourInt1 < endHourInt2) {
                resultEndHour = endHourInt1;
                // i1被处理完了
                i1++;
            } else {
                resultEndHour = endHourInt2;
                // i2被处理完
                i2++;
            }
            mergeResult.add(new Tuple(resultStartHour, resultEndHour, fee1.get(2), fee2.get(2)));
        }
        return mergeResult;
    }

}
