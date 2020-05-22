/**
 * 归并排序算法
 */
public class MergeSort {
    public static void merge_sort(int[] data){
        if (data.length <= 0){
            return;
        }
        merge_sort_c(data,0 , data.length-1);
    }
    public static void merge_sort_c(int[] data,int start,int end){
        if (start >= end) {return;}
        int q = (start+end)/2;
        merge_sort_c(data,start,q);
        merge_sort_c(data,q+1 ,end);
        merge(data,q,start,end);
    }
    public static void merge(int[]  data,final int q,final int start,final int end){
        //左边数组的指针
        int arr1POint = start;
        //右边数组的指针
        int arr2Point = q+1;
        int[] temp = new int[end-start+1];
        int tempPoint = 0;
        while (arr1POint <= q && arr2Point <= end){
            //如果相等 则把左边的也先放进去,这样可以保证该排序为稳定排序
            if (data[arr1POint] <= data[arr2Point]) {
                temp[tempPoint++] = data[arr1POint++];
            }
            else {
                temp[tempPoint++] = data[arr2Point++];
            }
        }
        int raminStart = arr1POint;
        int raminEnd = q;
        if (arr2Point <= end){
            raminStart = arr2Point;
            raminEnd = end;
        }
        while (raminStart <= raminEnd){
            temp[tempPoint++] = data[raminStart++];
        }
        for(int i=0;i <= end-start ; i++){
            data[start+i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] data = QuickSortArithmetic.makeIntArray(1);
        merge_sort(data);
        QuickSortArithmetic.printArray(data);
    }
}
