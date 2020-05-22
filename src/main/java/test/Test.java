package test;

import com.sun.deploy.util.StringUtils;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;

public class Test {
    public static void main(String[] args) {

    }

    public List<Side> readFile(){
        return new ArrayList<>();
    }

    public Tree makeTree(){
        Tree root = new Tree("中国");
        // 省：市，市：区的键值对
        List<Side> datas = readFile();
        // 存储所有一级和二级的关系(省-市关系)
        List<Side> oneLevelAndTwoLevel = new ArrayList<>();
        // 存储所有二级和三级的关系(市-区关系)
        List<Side> twoLevelAndThreeLevel = new ArrayList<>();

        findLevel(datas,oneLevelAndTwoLevel,twoLevelAndThreeLevel);

        doMakeTree(root,oneLevelAndTwoLevel,twoLevelAndThreeLevel);

        return root;
    }

    private void doMakeTree(Tree root, List<Side> oneLevelAndTwoLevel, List<Side> twoLevelAndThreeLevel) {
        oneLevelAndTwoLevel.forEach(side->{
            Tree oneLevel = new Tree(side.getPreName());
            //找出该节点的所有二级节点
            oneLevelAndTwoLevel.forEach(side1 -> {
                if (side.getPreName().equals(side1.getPreName())){
                    Tree twoLevel = new Tree(side1.getNextName());
                    oneLevel.addChild(twoLevel);
                }
            });
            //将该二级节点连接至根节点
            root.addChild(oneLevel);
        });
        // 所有一级树节点
        List<Tree> oneLevels = root.getChilds();
        // 遍历所有一级节点
        oneLevels.forEach(oneTree->{
            List<Tree> twoTrees = oneTree.getChilds();
            // 遍历所有二级节点
            twoTrees.forEach(twoTree->{
                // 遍历所有二-三级边
                twoLevelAndThreeLevel.forEach(side -> {
                    // 当该边的上级节点为当前的二级节点时,将该边的next作为三级节点连结该二级节点
                    if (twoTree.getName().equals(side.getPreName())){
                        Tree threeLevel = new Tree(side.getNextName());
                        // 添加三级节点
                        twoTree.addChild(threeLevel);
                    }
                });
            });
        });
    }

    private void findLevel(List<Side> datas, List<Side> oneLevelAndTwoLevel, List<Side> twoLevelAndThreeLevel) {
        //对于datas数据的一份完整拷贝
        List<Side> duplicate = new ArrayList<Side>();
        Collections.copy(datas,duplicate);
        //所有处于二级或者三级的地区的名字
        Set<String> twoOrThreeLevelNameSet = new HashSet<>();
        datas.forEach(side->{
            twoOrThreeLevelNameSet.add(side.getNextName());
        });
        datas.forEach(side->{
            if (twoOrThreeLevelNameSet.contains(side.getPreName())){
                //如果某条边的上级在二级三级名称集合中，那该边定为二-三级边(且数据中不存在三级-四级)
                twoLevelAndThreeLevel.add(side);
            }else {
                //否则这条边就是一级-二级边
                oneLevelAndTwoLevel.add(side);
            }
        });
    }


    public boolean isValid(Tree root, String oneLevel, String twoLevel, String threeLevel){
        if (!isBlank(oneLevel)) {
            List<Tree> oneTrees = root.getChilds();
            Tree matchedOneTree = null;
            for (Tree oneTree : oneTrees) {
                if (oneLevel.equals(oneTree.getName())){
                    matchedOneTree = oneTree;
                }
            }
            if (matchedOneTree == null){
                return false;
            }
            //匹配第二级
            if (!isBlank(twoLevel)){
                List<Tree> twoTrees = matchedOneTree.getChilds();
                Tree matchedTwoTree = null;
                for (Tree twoTree : twoTrees) {
                    if (oneLevel.equals(twoTree.getName())){
                        matchedTwoTree = twoTree;
                    }
                }
                if (matchedTwoTree == null){
                    return false;
                }
                //匹配第三级
                if (!isBlank(threeLevel)){
                    List<Tree> threeTrees = matchedTwoTree.getChilds();
                    Tree matchedThreeTree = null;
                    for (Tree threeTree : threeTrees) {
                        if (oneLevel.equals(threeTree.getName())){
                            matchedThreeTree = threeTree;
                        }
                    }
                    if (matchedThreeTree == null){
                        return false;
                    }
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public boolean isBlank(String s){
        return s == null || s.length()==0 || "".equals(s.trim());
    }
}
