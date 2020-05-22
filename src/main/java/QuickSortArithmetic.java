import java.util.Random;

public class QuickSortArithmetic {
    public static void qs_sort(int[] data){
        if (data.length <= 0 ){
            return;
        }
        qs_sort_c(data,0 ,data.length-1 );
    }

    private static void qs_sort_c(int[] data,int start,int end) {
        if(start > end) {return;}

        //对start - end 区间的数据进行分区 并且返回中间值的索引
        int q = partition(data,start,end);

        qs_sort_c(data, start,q-1 );
        qs_sort_c(data, q+1,end);
    }

    private static int partition(int[] data, int start, int end) {
        int pivot = data[end];
        int i = start;
        for (int j = start ; j < end; j++){
            if (data[j] < pivot){
                //如果小于pivot的 将值移到i左侧去
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i++;
            }
        }
        int temp = data[end];
        data[end] = data[i];
        data[i] = temp;
        return i;
    }

    public static int[] makeIntArray(int size){
        int[] data = new int[size];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100);
        }
        return data;
    }
    public static void main(String[] args) {
        int[] data = makeIntArray(1000000);
//        printArray(data);
        qs_sort(data);
        printArray(data);
    }

    public static void printArray(int[] data){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(data[i]+"");
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }

}
