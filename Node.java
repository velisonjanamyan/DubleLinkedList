package LinkedList;

public class Node<T> {
    private T val;
    private Node<T> prev;
    private Node<T> next;

    Node(T val, Node<T> prev, Node<T> next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }

    Node(T val) {
        this.val = val;
    }

    Node() {}

    public void setVal(T val) {
        this.val = val;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return this.prev;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public T getVal() {
        return this.val;
    }

}
