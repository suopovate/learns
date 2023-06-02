package vt.leetcode.leetcode.editor.cn;

import com.sun.xml.internal.bind.v2.model.core.ID;

class SortAnArray {
    public static void main(String[] args) {
        Solution solution = new SortAnArray().new Solution();
        //        solution.sortArray(new int[]{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 });
        solution.sortArray(new int[]{ 5, 2, 3, 1 });
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int[] sortArray(int[] nums) {
            //            quickSort(nums, 0, nums.length - 1);
            //            return nums;
            //            return mergeSort(nums, 0, nums.length - 1);
            return sortByHead(nums);
        }

        public int[] mergeSort(int[] nums, int lo, int hi) {
            if (lo > hi || lo < 0 || hi >= nums.length) {
                return new int[0];
            }
            if (lo == hi) {
                return new int[]{ nums[lo] };
            }
            int mid = (lo + hi) / 2;
            int[] lNums = mergeSort(nums, lo, mid);
            int[] rNums = mergeSort(nums, mid + 1, hi);
            int[] results = new int[lNums.length + rNums.length];
            int li = 0, ri = 0, i = 0;
            if (lNums.length > 0 && rNums.length > 0) {
                while (li < lNums.length && ri < rNums.length) {
                    if (lNums[li] <= rNums[ri]) {
                        results[i] = lNums[li];
                        li++;
                    } else {
                        results[i] = rNums[ri];
                        ri++;
                    }
                    i++;
                }
            }
            // 同时只会有一个数组被处理完
            if (ri < rNums.length) {
                for (; ri < rNums.length; ri++, i++) {
                    results[i] = rNums[ri];
                }
            }
            if (li < lNums.length) {
                for (; li < lNums.length; li++, i++) {
                    results[i] = lNums[li];
                }
            }
            return results;
        }

        public void quickSort(int[] nums, int lo, int hi) {
            if (lo >= hi) {
                return;
            }
            int p = getPartition2(nums, lo, hi);
            quickSort(nums, lo, p - 1);
            quickSort(nums, p + 1, hi);
        }

        private int getPartition(int[] nums, int lo, int hi) {
            int pivot = nums[hi];
            // 最终分区所在的位置
            // 在遍历的过程中，从一开始，直到结束，我们都假设这个指针，指向比pivot大的那个值
            int pi = lo;
            for (int i = lo; i < hi; i++) {
                // 比pv小，i却大些，交换位置
                if (nums[i] <= pivot) {
                    swap(nums, i, pi);
                    pi++;
                }
            }
            swap(nums, pi, hi);
            return pi;
        }

        private int getPartition2(int[] nums, int lo, int hi) {
            int pivot = nums[lo];
            // 最终分区所在的位置
            // 在遍历的过程中，从一开始，直到结束，我们都假设这个指针，指向比pivot大的那个值
            int pi = lo + 1;
            for (int i = lo + 1; i <= hi; i++) {
                // 比pv小，i却大些，交换位置
                if (nums[i] <= pivot) {
                    swap(nums, i, pi);
                    pi++;
                }
            }
            swap(nums, pi - 1, lo);
            return pi;
        }

        private int getPartition3(int[] nums, int lo, int hi) {
            int pivot = nums[lo];
            // 最终分区所在的位置
            // 在遍历的过程中，从一开始，直到结束，我们都假设这个指针，指向比pivot大的那个值
            int pi = lo + 1;
            int less = lo + 1;
            int more = hi + 1;
            while (pi < more) {

            }
            for (int i = lo + 1; i <= hi; i++) {
                // 比pv小，i却大些，交换位置
                if (nums[i] <= pivot) {
                    swap(nums, i, pi);
                    pi++;
                }
            }
            swap(nums, pi - 1, lo);
            return pi;
        }

        private int[] sortByHead(int[] nums) {
            Heap heap = new Heap(nums.length);
            for (int i = 0; i < nums.length; i++) {
                heap.add(nums[i]);
            }
            for (int i = 0; i < nums.length; i++) {
                nums[i] = heap.get();
            }
            return nums;
        }

        private void swap(int[] nums, int i1, int i2) {
            int temp = nums[i1];
            nums[i1] = nums[i2];
            nums[i2] = temp;
        }

    }

    public class Heap {

        int[] heap;
        int size;
        int capacity;

        public Heap(int capacity) {
            this.heap = new int[capacity];
            size = 0;
            this.capacity = capacity;
        }

        public void add(int val) {
            heap[size++] = val;
            heapifyUp(size - 1);
        }

        public int get() {
            int val = heap[0];
            // 删掉头结点，尾结点代替头结点
            heap[0] = heap[--size];
            // 向下堆化
            heapifyDown(0);
            return val;
        }

        public void heapifyUp(int i) {
            if (i >= size || i <= 0) {
                return;
            }
            int pindex = parentIndex(i);
            if (pindex >= 0 && heap[pindex] > heap[i]) {
                swap(pindex, i);
                heapifyUp(pindex);
            }
        }

        public void heapifyDown(int i) {
            if (i >= size) {
                return;
            }
            int li = leftChildIndex(i);
            int mini = i;
            int minv = heap[i];
            if (li < size && heap[li] < minv) {
                mini = li;
                minv = heap[li];
            }
            int ri = rightChildIndex(i);
            if (ri < size && heap[ri] < minv) {
                mini = ri;
                minv = heap[ri];
            }
            // 1. 两个子节点都大于父节点
            // 2. 也可能是当前节点是叶子节点，没有子节点
            if (mini == i) {
                return;
            }
            swap(mini, i);
            heapifyDown(mini);
        }

        public int parentIndex(int index) {
            return (index - 1) / 2;
        }

        public int leftChildIndex(int index) {
            return index * 2 + 1;
        }

        public int rightChildIndex(int index) {
            return index * 2 + 2;
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)

}
