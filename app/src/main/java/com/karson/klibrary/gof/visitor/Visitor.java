package com.karson.klibrary.gof.visitor;

public interface Visitor {

    void visit(Candy candy);

    void visit(Wine wine);

    void visit(Fruit fruit);
}
