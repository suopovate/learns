package vt.leetcode.str;

/**
 * @author vate
 */
public class ArrMergeAlgorithm {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        while (i < n){
            nums1[m+i] = nums2[i];
            for(int j = m+i; j > 0; j--){
                if (nums1[j] < nums1[j-1]){
                    swap(nums1,j,j-1);
                }else {
                    break;
                }
            }

        }
    }

    private static void swap(int[] nums1, int current, int pre) {
        int t = nums1[current];
        nums1[current] = nums1[pre];
        nums1[pre] = t;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,3,4};
        merge(nums1,3,nums2,nums2.length);
        System.out.println(nums1);
    }
}
