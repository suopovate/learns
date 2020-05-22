package sort;

public interface BaseSortor {
    int[] sortIntAsc(int[] datas);
    default void swapInt(int[] datas,int x,int y){
        if (x != y){
            int temp;
            temp = datas[x];
            datas[x] = datas[y];
            datas[y] = temp;
        }
    }
    default boolean isEmptyArr(int[] datas){
        return datas == null || datas.length ==0;
    }
}
