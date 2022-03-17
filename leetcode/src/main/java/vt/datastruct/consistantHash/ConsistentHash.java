package vt.datastruct.consistantHash;

import lombok.Data;

import java.util.*;

/**
 * 一致性哈希介绍: https://vate.notion.site/860187888dda4ec5b9f5922cf429b40b
 */
public class ConsistentHash {

    /**
     * 哈希槽的大小
     */
    int slotSize = Integer.MAX_VALUE;

    Map<String, Node> nodeMap = new HashMap<>();

    ArrayList<Node> nodes;

    public int index(int hash) {
        return hash % slotSize;
    }

    public void insertNode(Node node) {
        nodeMap.put(node.getKey(), node);
        nodes.add(node);
        node.setIndex(index(node.hashCode()));
        nodes.sort(Node::compareTo);
    }

    public void removeNode(Node node) {
        nodeMap.remove(node.getKey());
        nodes.remove(node);
    }

    public int judgeKeyIndex(String key) {
        return index(Objects.hash(key));
    }

    public Node judgeKeyNode(String key) {
        int keyIndex = judgeKeyIndex(key);
        for (Node node : nodes) {
            if (node.getIndex() > keyIndex) {
                return node;
            }
        }
        return nodes.get(0);
    }

    @Data
    public class Node implements Comparable<Node> {

        String name;
        String address;
        String key;
        Integer index;

        List<Node> child;
        Node parent;

        @Override
        public int compareTo(Node node) {
            return this.index.compareTo(node.index);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return Objects.equals(getKey(), node.getKey());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getKey());
        }

    }

}
