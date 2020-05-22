package tree;

import java.security.PrivateKey;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;

public class VateBinaryTree<T extends Comparable<T>> {

    private VateBinaryTree parent = null;
    private VateBinaryTree left = null;
    private VateBinaryTree right = null;
    private T data;

    public T find(T obj){
        Objects.requireNonNull(obj);VateBinaryTree<T> currNode = this;

        while (currNode != null){
            T cData = currNode.data;
            if (cData == null){
                return null;
            }
            int compareResult = cData.compareTo(obj);
            if (0 == compareResult){
                return data;
            }
            if (compareResult < 0){
                currNode = currNode.right;
                continue;
            }
            if (compareResult > 0){
                currNode = currNode.left;
            }
        }
        return null;
    }

    public void add(T obj){
        Objects.requireNonNull(obj);
        VateBinaryTree<T> currNode = this;
        while (true){
            if (Objects.isNull(currNode.data)){
                currNode.data = obj;
                return;
            }
            else {
                T cData = currNode.data;
                int compareResult = cData.compareTo(obj);
                if (compareResult == 0) {
                    data = obj;
                    return;
                }
                //如果当前节点的数据比传入节点小,往右子树递归
                if (compareResult < 0){
                    if (Objects.isNull(currNode.right)){
                        VateBinaryTree rNode = new VateBinaryTree();
                        rNode.setParent(currNode);
                        currNode.right = rNode;
                    }
                    currNode = currNode.right;
                }
                //如果当前节点的数据比传入节点大,往左子树递归
                if (compareResult > 0){
                    if (Objects.isNull(currNode.left)){
                        VateBinaryTree lNode = new VateBinaryTree();
                        lNode.setParent(currNode);
                        currNode.left = lNode;
                    }
                    currNode = currNode.left;
                }
            }
        }
    }

    public boolean delete(T obj){
        Objects.requireNonNull(obj);
        VateBinaryTree<T> currNode = this;
        while (currNode != null){
            T cData = currNode.data;
            if (Objects.isNull(cData)){
                return false;
            }
            int compareResult = cData.compareTo(obj);
            if (compareResult == 0){
                //找到了 并且把他的右子树中最左的节点置为当前节点的位置
                if (!Objects.isNull(currNode.right)){
                    VateBinaryTree<T> llNode = findLastLeftNode(currNode.right);
                    currNode.data = llNode.data;
                    //如果这个右子树的最左节点没有子树了 就直接删除即可
                    llNode.data = null;
                    if (llNode.right == null){
                        llNode.parent.left = null;
                    }
                    else {
                        //如果该节点仍有右子树，将该树的右子树代替该节点成为父亲的左子树
                        llNode.parent.left = llNode.right;
                    }
                }
                return true;
            }
            if (compareResult < 0){
                currNode = currNode.right;
            }else {
                currNode = currNode.left;
            }
        }
        return false;
    }

    public static VateBinaryTree findLastLeftNode(VateBinaryTree root){
        Objects.requireNonNull(root);
        VateBinaryTree c = root;
        while (true){
            if (!Objects.isNull(c.left)){
                c = c.left;
            }
            else {
                return c;
            }
        }
    }

    public static VateBinaryTree findLastRightNode(VateBinaryTree root){
        Objects.requireNonNull(root);
        VateBinaryTree c = root;
        while (true){
            if (!Objects.isNull(c.right)){
                c = c.right;
            }
            else {
                return c;
            }
        }
    }

    public VateBinaryTree getParent() {
        return parent;
    }

    public void setParent(VateBinaryTree parent) {
        this.parent = parent;
    }

    public VateBinaryTree getLeft() {
        return left;
    }

    public void setLeft(VateBinaryTree left) {
        this.left = left;
    }

    public VateBinaryTree getRight() {
        return right;
    }

    public void setRight(VateBinaryTree right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static void main(String[] args){
        TreeMap treeMap = new TreeMap();
        VateBinaryTree vateBinaryTree = new VateBinaryTree();
        String test1 = "test1";
        String test2 = "test2";
        String test3 = "test3";
        vateBinaryTree.add(test1);
        vateBinaryTree.add(test3);
        vateBinaryTree.add(test2);
        vateBinaryTree.find(test1);
    }
}
