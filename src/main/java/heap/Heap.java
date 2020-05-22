package heap;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.logging.Level;

/**
 * 堆的概念：
 * 堆就是一棵树：
 * a.完全二叉树
 * b.所有节点必须大于(小于)其任意子树z
 */
public class Heap<T extends Comparable> {

    public Heap(int capacity) {
        this.capacity = capacity;
        treeDatas = (T[]) new Comparable[capacity+1];
    }

    /**
     * 使用数组来存储这棵完全二叉树
     * 特点：
     * a.为了简化操作，牺牲第一个0索引，该索引不存储任何元素
     * b.假设当前节点对应数组索引为i,每一个节点的左子树：i*2 右子树：i*2+1
     */
    T[] treeDatas;
    /**
     * 堆的容量
     */
    int capacity;
    /**
     * 当前已经存储的元素数量
     */
    int count = 0;

    public boolean add(T obj) {
        if (count < capacity) {
            //将元素插入到最尾树节点
            treeDatas[++count] = obj;
            //堆化操作
            heapify_downToUp(count);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param ele the Object you want to delete
     * @return the deleted Object
     */
    public boolean del(T ele) {
        assert ele == null;
        int index = 1;
        if (count >= 1){
            while (index <= count){
                int cIndex = index++;
                if (treeDatas[cIndex].equals(ele)){
                    //先跟最后一个元素进行交换，并且删除最后一个元素
                    swap(cIndex,count);
                    //删除最后一个元素
                    treeDatas[count--] = null;
                    //堆化
                    if (count < 1){
                        //如果删完以后数组已空，则不需要再堆化。
                        return true;
                    }
                    if (cIndex > count/2){
                        //如果删除的是叶子节点则进行下往上的堆化
                        heapify_downToUp(cIndex);
                    }
                    //否则从上往下堆化
                    heapify_upToDown(cIndex);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从指定的索引将该子树从下往上进行堆化
     * @param index
     */
    private void heapify_downToUp(int index) {
        if (index <= 1){
            return;
        }
        int cIndex = index;
        int pIndex = index/2;
        while (pIndex >= 1){
            if (treeDatas[cIndex].compareTo(treeDatas[pIndex]) > 0){
                //如果该值大于父节点的值，交换
                swap(cIndex,pIndex);
                cIndex = pIndex;
                pIndex = pIndex/2;
            }else {
                break;
            }
        }
    }

    /**
     * 从指定的索引将该子树从上往下进行堆化
     * @param index
     */
    private void heapify_upToDown(int index) {
        if (index < 1 || index >= count){
            return;
        }
        while (true){
            int cIndex = index;
            // 与左子节点比较
            if (index*2 <= count && treeDatas[index].compareTo(treeDatas[index*2]) < 0){
                cIndex = index*2;
            }
            // 与右子节点比较
            if (index*2+1 <= count && treeDatas[cIndex].compareTo(treeDatas[index*2+1]) < 0){
                cIndex = index*2+1;
            }
            //不需要再往下交换
            if (cIndex == index){
                break;
            }
            swap(index,cIndex);
            index = cIndex;
        }
    }

    /**
     * 从指定的索引将该子树从上往下进行堆化
     * @param index 指定得元素位置
     * @param end 堆化的最尾索引
     */
    private void heapify_upToDown(int index,int end) {
        if (index < 1 || index >= end){
            return;
        }
        while (true){
            int cIndex = index;
            if (index*2 <= end && treeDatas[index].compareTo(treeDatas[index*2]) < 0){
                cIndex = index*2;
            }
            if (index*2+1 <= end && treeDatas[cIndex].compareTo(treeDatas[index*2+1]) < 0){
                cIndex = index*2+1;
            }
            if (cIndex == index){
                break;
            }
            swap(index,cIndex);
            index = cIndex;
        }
    }

    private void swap(int x,int y){
        T temp = treeDatas[x];
        treeDatas[x] = treeDatas[y];
        treeDatas[y] = temp;
    }

    public void sort(){
        int endIndex = count;
        while (endIndex > 1){
            swap(1,endIndex--);
            heapify_upToDown(1,endIndex);
        }
    }

    public String getTreeString(){
        int level = 0;
        int levelCount;
        int cIndex = 1;
        StringBuilder treeString = new StringBuilder();
        while (cIndex <= count){
            levelCount = 1;
            for (;levelCount <= (Math.pow(2,level)) && cIndex <= count;levelCount++){
                treeString.append(treeDatas[cIndex++].toString()+" ");
            }
            level++;
            treeString.append("\r\n");
        }
        return treeString.toString();
    }

    @Override
    public String toString() {
        return getTreeString();
    }
    public String printTreeDataAsArr(){
        return treeDatas.toString();
    }
}