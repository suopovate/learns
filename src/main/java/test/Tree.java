package test;

import java.util.*;

/**
 * 以中国为根节点的省市级树
 */
public class Tree {
    private String name;
    private List<Tree> childs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tree> getChilds() {
        return childs;
    }

    public void setChilds(List<Tree> childs) {
        this.childs = childs;
    }

    public void addChild(Tree child){
        childs.add(child);
    }

    public void delChild(Tree child){
        childs.remove(child);
    }

    public Tree(String name) {
        this.name = name;
        this.childs = new LinkedList<Tree>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tree)) return false;
        Tree tree = (Tree) o;
        return name.equals(tree.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
