package com.karson.klibrary.gof.composite;

public class CompositeClient {
    public static void main(String[] args) {
        CompositeNode compositeNode = new CompositeNode("主枝");
        CompositeNode subCompositeNode = new CompositeNode("次枝");
        subCompositeNode.addNode(new ChildNode("次枝-叶子1"));
        compositeNode.addNode(subCompositeNode);
        compositeNode.addNode(new ChildNode("主枝-叶子1"));
        compositeNode.addNode(new ChildNode("主枝-叶子2"));
        compositeNode.tree();
    }
}
