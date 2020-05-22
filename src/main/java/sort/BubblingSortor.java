package sort;

/**
 * @author vate
 * 冒泡算法
 */
public class BubblingSortor implements BaseSortor{

    /**
     *
     */
    @Override
    public int[] sortIntAsc(int[] datas) {
        if (isEmptyArr(datas) || datas.length < 2){
            return datas;
        }
        for (int i = 0; i <= datas.length -1 ; i++){
            for (int j = 0; j < datas.length - 1 - i ; j++){
                if (datas[j] > datas[j+1]){
                    //if the pre big than next
                    //we should swap the pre and the next int
                    swapInt(datas,j,j+1);
                }
            }
        }
        return datas;
    }
}
