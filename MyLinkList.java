package LinkedList;


import java.io.*;

public class MyLinkList <T> implements Serializable {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    MyLinkList() {}

    MyLinkList(Node<T> type) {
        this.head = type;
        this.tail = type;
    }

    public Node<T> getFirst() {
        return head;
    }

    public Node<T> getLast() {
        if(this.tail == null) {
            return null;
        }

        return tail;
    }

    public boolean removeFirst() {
        if(this.head == null) {
            return false;
        }

        this.head = this.head.getNext();
        if(this.head == null) this.tail = null;
        this.head.setPrev(null);
        this.size--;
        return true;
    }

    public boolean removeLast() {
        if(this.tail == null) {
            return false;
        }

        this.size--;
        if(this.tail == this.head) {
            this.head = null;
            this.tail = null;
            return true;
        }

        this.tail = this.tail.getPrev();
        this.tail.setNext(null);
        return true;
    }

    public void addFirst(T val) {
        this.size++;
        if (this.head == null) {
            this.head = new Node<>(val);
            this.tail = this.head;
            return;
        }
        Node<T> node = new Node<>(val);
        node.setNext(this.head);
        this.head.setPrev(node);
        head = node;
    }

    public void addLast(T val) {
        this.size++;
        if(this.tail == null) {
            this.head = new Node<>(val);
            this.tail = this.head;
            return;
        }

        tail.setNext(new Node<>(val, this.tail, null));
        this.tail = this.tail.getNext();
    }

    public boolean contains(T val) {
        Node<T> tmp1 = this.head;
        Node<T> tmp2 = this.tail;

        while (tmp1 != tmp2) {
            if(tmp1.getVal() == val) {
                return true;
            }
            if(tmp2.getVal() == val) {
                return true;
            }
            tmp1 = tmp1.getNext();
            tmp2 = tmp2.getPrev();
        }

        if(tmp1.getVal() == val) {
            return true;
        }

        return false;
    }

    public int getSize() {
        return this.size;
    }

    public void remove(Node<T> node) {
        if(!this.contains(node.getVal())) return;

        this.size--;
        if(node == this.head && node == this.tail) {
            this.head = this.tail = null;
        }

        if(node == this.head) {
            this.head = node.getNext();
            head.setPrev(null);
        }

        if(this.tail == node) {
            this.tail = node.getPrev();
            this.tail.setNext(null);
        }

        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
    }

    public void clear() {
        this.head = this.tail = null;
        size = 0;
    }

    public Node<T> set(int index, T val) {
        if(this.head == null) {
            return null;
        }

        if(index < 0 || index >= this.getSize()) {
            throw new CostomeExeption();
        }

        Node<T> tmp = this.head;
        while (index > 0) {
            tmp = tmp.getNext();
            index--;
        }

        Node<T> node = new Node<>(val, tmp.getPrev(), tmp.getNext());
        if(node.getPrev() != null) {
            node.getPrev().setNext(node);
        }
        if(node.getNext() != null) {
            node.getNext().setPrev(node);
        }

        tmp.setPrev(null);
        tmp.setNext(null);
        return tmp;
    }

    public void add(int index, T val) {
        if(index < 0 || index > this.size) {throw new CostomeExeption();}

        Node<T> tmp = this.head;
        while (index > 0) {
            tmp = tmp.getNext();
            index--;
        }

        Node<T> newNode = new Node<>(val);
        newNode.setPrev(tmp.getPrev());
        newNode.setNext(tmp);
        tmp.setPrev(newNode);

        if(tmp.getPrev() != null) {
            tmp.getPrev().setNext(newNode);
        }

    }

    public void writeToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> MyLinkList<T> readToFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (MyLinkList<T>) ois.readObject();
        }
    }

}
