package vt.leetcode.str;

/**
 * @author vate
 */
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeArr = mergeArr(nums1, nums2);
        if (mergeArr.length % 2 == 0) {
            int m1 = mergeArr.length / 2 - 1;
            int m2 = mergeArr.length / 2;
            return (mergeArr[m1] + mergeArr[m2]) / 2f;
        }else {
            return mergeArr[mergeArr.length / 2];
        }
    }

    public int[] mergeArr(int[] nums1, int[] nums2) {

        int[] result = new int[nums1.length + nums2.length];
        int i1 = 0, i2 = 0, ir;
        for (ir = 0; ir < result.length; ir++) {
            if (i1 >= nums1.length || i2 >= nums2.length) {
                break;
            }
            int num1 = nums1[i1], num2 = nums2[i2];
            if (num1 <= num2) {
                result[ir] = num1;
                i1++;
            } else {
                result[ir] = num2;
                i2++;
            }
        }

        if (i1 < nums1.length) {
            while (i1 < nums1.length) {
                result[ir++] = nums1[i1++];
            }
        } else if (i2 < nums2.length) {
            while (i2 < nums2.length) {
                result[ir++] = nums2[i2++];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(new int[]{1,2,3,4},new int[]{1,2,3,4}));
    }
}
