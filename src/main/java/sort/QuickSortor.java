package sort;

public class QuickSortor implements BaseSortor {
    @Override
    public int[] sortIntAsc(int[] datas) {
        quick_sort(datas);
        return datas;
    }

    private void quick_sort(int[] datas){
        quick_sort_c(datas,0,datas.length-1);
    }

    private void quick_sort_c(int[] datas, int start, int end){
        if (end > start){
            int pivot = partition(datas,start,end);
            if (pivot == end){
                //如果分区后发现pivot位置为end则说明
                return;
            }
            //此处一定要:pivot -1 一开始忽略了这个
            quick_sort_c(datas,start,pivot-1);
            quick_sort_c(datas,pivot+1,end);
        }
    }

    /**
     * @param datas 数据数组
     * @param start 需要排序的数据所在分区的起始位置
     * @param end 结束位置
     * @return 当前pivot所属的位置
     * 解释 i 永远指向的都是大于pivot的值
     *     j 是移动指针，遍历从start - end-1的元素
     */
    private int partition(int[] datas, int start, int end){
        int i = start,j = start;
        int pivot = end;
        int pivotValue = datas[pivot];
        if (start < end){
            //j不会一直找到end...因为end是pivot
            for (; j < end; j++){
                if (datas[j] < pivotValue){
                    //如果当前j指向的元素小于pivot,则将该元素和当前i所在元素进行交换，并且将i往后移动
                    swapInt(datas,i++,j);
                }
            }
            //i指向的元素和pivot指向的元素值进行交换
            pivot = i;
            swapInt(datas,pivot,end);
        }
        return pivot;
    }
}
