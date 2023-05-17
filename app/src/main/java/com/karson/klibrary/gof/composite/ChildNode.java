package com.karson.klibrary.gof.composite;

public class ChildNode extends Node {

    public ChildNode(String name) {
        super(name);
    }

    @Override
    public void addNode(Node node) {
        System.out.println("不能添加子节点");
    }

}
