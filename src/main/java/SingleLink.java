public class SingleLink<T>{
    private SingleNode<T> headNode;
    private SingleNode<T> tailNode;
    private int size;
    public SingleLink(){
        headNode = new SingleNode();
        tailNode = new SingleNode();
        tailNode.next = headNode;
        size = 0;
    }

    public SingleLink<T> add(T value){
        SingleNode<T> currNode = new SingleNode(value,null);
        if (tailNode.next == null) {
            headNode.next = currNode;
            tailNode.next = currNode;
            size++;
            return  this;
        }
        tailNode.next.next = currNode;
        tailNode.next = currNode;
        size++;
        return this;
    }

    public SingleNode<T> remove(int index){
        if (size == 0){
            throw new IllegalStateException("链表中不存在任何数据！操作被拒绝！");
        }
        if (index < 0){
            throw new IllegalArgumentException(String.format("传入的索引值为非法值！传入值为：%d", index));
        }
        if (index >= size){
            throw new IllegalArgumentException(String.format("传入的索引值大于当前的最大索引值！传入值为：%d,最大索引值为: %d", index,size-1));
        }
        SingleNode<T> currNode = headNode;
        SingleNode<T> preNode = null;
        //find the element of index
        for (int i=0; i <= index;i++){
            preNode = currNode;
            currNode = currNode.next;
        }
        if (tailNode.next == currNode){
            tailNode.next = preNode;
            preNode.next = currNode.next;
            return currNode;
        }
        preNode.next = currNode.next;
        return currNode;
    }



    public T getValue(int index){
        if (size == 0){
            return null;
        }
        if (index < 0){
            return null;
        }
        if (index >= size){
            return null;
        }
        SingleNode<T> currNode = headNode;
        //find the element of index
        for (int i=0; i <= index;i++){
            currNode = currNode.next;
        }
        return currNode.value;
    }

    public SingleLink<T> inverse(){
        SingleNode<T> currNode = null;
        SingleNode<T> tempNode = null;
        if (headNode.next == null){
            return this;
        }
        if (size <= 1){
            return this;
        }
        //first node
        currNode = headNode.next;
        tailNode.next = currNode;
        //third node
        tempNode = currNode.next.next;
        //seconder point to first
        currNode.next.next = currNode;
        //head point to seconder
        headNode.next = currNode.next;
        //first node point to null
        currNode.next = null;
        currNode = tempNode;
        while (currNode !=null && currNode.next != null){
            tempNode = currNode.next.next;
            currNode.next.next = currNode;
            currNode.next = headNode.next;
            currNode = tempNode;
        }
        //end node
        if (tempNode != null){
            tempNode.next = headNode.next;
            headNode.next = tempNode;
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder allNodeValue = new StringBuilder();
        SingleNode<T> currNode = headNode;
        if (currNode.next == null){
            return "[]";
        }

        allNodeValue.append("[");
        while (currNode.next != null){
            currNode = currNode.next;
            allNodeValue.append(currNode.value.toString());
            allNodeValue.append(",");
        }
        allNodeValue.deleteCharAt(allNodeValue.length()-1);
        allNodeValue.append("]");
        return allNodeValue.toString();
    }

    private static class SingleNode<T>{
        public SingleNode(T value,SingleNode next){
            this.value = value;
            this.next = next;
        }
        public SingleNode(T value){
            this.value = value;
            this.next = null;
        }
        public SingleNode(){
            this.value = null;
            this.next = null;
        }

        private SingleNode next;
        private T value;
    }
}
