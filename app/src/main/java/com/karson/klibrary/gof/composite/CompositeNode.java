package com.karson.klibrary.gof.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeNode extends Node {
    private final List<Node> mNodes = new ArrayList<>();

    public CompositeNode(String name) {
        super(name);
    }

    @Override
    public void addNode(Node node) {
        mNodes.add(node);
    }

    @Override
    protected void tree() {
        super.tree();
        for (Node node: mNodes) {
            node.tree();
        }
    }
}
