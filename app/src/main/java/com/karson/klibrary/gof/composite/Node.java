package com.karson.klibrary.gof.composite;

public abstract class Node {
    protected String name;

    protected Node(String name) {
        this.name = name;
    }

    protected abstract void addNode(Node node);

    protected void tree() {
        System.out.println(this.name);
    }
}
