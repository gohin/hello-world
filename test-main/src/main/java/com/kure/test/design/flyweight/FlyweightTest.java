package com.kure.test.design.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightTest {

    public static void main(String[] args) {

        String[] treeNames = {"柳树", "小白杨", "梧桐"};
        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                new TreeNode(x, y, TreeFactory.getTree(treeNames[y]));
            }
        }
    }
}


class Tree{
    private String name;

    public Tree(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class TreeNode{
    private int x;
    private int y;

    private Tree tree;

    public TreeNode(int x, int y, Tree tree) {
        this.x = x;
        this.y = y;
        this.tree = tree;
    }
}


class TreeFactory{
    private static Map<String, Tree> map = new HashMap<>();

    public static Tree getTree(String name) {

        if (map.containsKey(name)) {
            System.out.println("获取tree:" + name);
            return map.get(name);
        }

        Tree tree = new Tree(name);
        System.out.println("创建tree:" + name);
        map.put(name, tree);
        return tree;
    }
}