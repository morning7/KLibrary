package com.karson.klibrary.gof.adapter;

public interface TriplePin {
    /**
     *
     * @param l live 火线
     * @param n null 零线
     * @param e earth 地线
     */
    void electrify(int l, int n, int e);
}
