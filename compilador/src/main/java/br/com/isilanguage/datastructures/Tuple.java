package br.com.isilanguage.datastructures;


public class Tuple<E1, E2> {
    private final E1 e1;
    private final E2 e2;

    public Tuple(E1 e1, E2 e2){
        this.e1 = e1;
        this.e2 = e2;
    }

    public E1 getE1() {
        return e1;
    }

    public E2 getE2() {
        return e2;
    }

    public String toString() {
        return String.format("(%s, %s)", e1, e2);
    }
}
