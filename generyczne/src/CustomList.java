import java.util.AbstractList;
import java.util.Iterator;

public class CustomList<E> extends AbstractList<E> {
    private class Node {
        E value;
        Node next;

        public Node(E value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public CustomList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public void addLast(E value) {
        Node newNode = new Node(value);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E getLast() {
        if (tail == null) {
            throw new IllegalStateException("Lista jest pusta");
        }
        return tail.value;
    }

    public void addFirst(E value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public E getFirst() {
        if (head == null) {
            throw new IllegalStateException("Lista jest pusta");
        }
        return head.value;
    }

    public E removeFirst() {
        if (head == null) {
            throw new IllegalStateException("Lista jest pusta");
        }
        E value = head.value;
        size--;
        head = head.next;
        if (head == null)
            tail = null;
        return value;
    }

    public E removeLast() {
        if (tail == null) {
            throw new IllegalStateException("Lista jest pusta");
        }
        E value = tail.value;
        size--;
        if (head == tail) {
            head = tail = null;
            return value;
        }

        Node curr = head;
        while (curr.next != tail) {
            curr = curr.next;
        }
        curr.next = null;
        tail = curr;
        return value;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Poza zakresem [0, "+size+")");
        }
        Node curr = head;
        for(int i=0; i<index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter(head);
    }

    private class Iter implements Iterator<E> {
        private Node current;

        public Iter(Node start) {
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E value = current.value;
            if (hasNext()) {
                current = current.next;
            }
            return value;
        }
    }
}
