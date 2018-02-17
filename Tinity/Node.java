package Tinity;
public abstract class Node {
    protected final MyFirstNodeScript c;

    public Node(MyFirstNodeScript main){
        this.c = main;
    }
    public abstract boolean validate();
    public abstract int execute();
}
