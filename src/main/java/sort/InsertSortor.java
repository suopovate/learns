package sort;

public class InsertSortor implements BaseSortor{
    @Override
    public int[] sortIntAsc(int[] datas) {
        if (isEmptyArr(datas) || datas.length < 2){
            return datas;
        }
        for(int i = 1; i <= datas.length - 1; i++){
            int j = i - 1;
            int currValue = datas[i];
            for (; j >= 0; j--){
                if (datas[j] > currValue){
                    // 如果当前元素大于当前待比较值 把当前元素 后移一位
                    datas[j+1] = datas[j];
                }else {
                    // 否则说明当前索引指向的值并未大于当前待比较值
                    // 应当把待比较值放入该索引后方
                    break;
                }
            }
            datas[j+1] = currValue;
        }
        return datas;
    }
}
