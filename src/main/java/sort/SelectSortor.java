package sort;

public class SelectSortor implements BaseSortor{
    @Override
    public int[] sortIntAsc(int[] datas) {
        int minValue,minIndex;
        for (int i = 0; i <= datas.length -1; i++){
            minIndex = i;
            minValue = datas[i];
            int j = i + 1;
            for (; j < datas.length - 1; j++){
                if (datas[j] < minValue){
                    minValue = datas[j];
                    minIndex = j;
                }
            }
            swapInt(datas,i,minIndex);
        }
        return datas;
    }
}
