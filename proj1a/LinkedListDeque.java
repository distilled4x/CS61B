public class LinkedListDeque<T> {
    public class Node {
        private Node prev;
        private Node next;
        private T item;

        public Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinal;
    private Node p;
    private int size;


    public LinkedListDeque() {
        this.size = 0;
        this.sentinal = new Node(null, sentinal, sentinal);
        this.p = sentinal;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        if (sentinal.next == null) {
            sentinal.next = new Node(item, sentinal, sentinal);
            sentinal.prev = sentinal.next;
        } else {
            sentinal.next = new Node(item, sentinal.next, sentinal);
            sentinal.next.next.prev = sentinal.next;
        }
        this.size++;

    }

    public void addLast(T item) {
        if (sentinal.prev == null) {
            sentinal.prev = new Node(item, sentinal, sentinal);
        } else {
            sentinal.prev = new Node(item, sentinal, sentinal.prev);
            sentinal.prev.prev.next = sentinal.prev;
        }
        this.size++;
    }

    public void printDeque() {
        Node p = sentinal.next;
        while (p.next.item != null) {
            System.out.println(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item + " ");
    }

    public T removeFirst() {
        if (sentinal.next.item != null) {
            T getItem = sentinal.next.item;
            if (size > 1) {
                sentinal.next.next.prev = sentinal;
                sentinal.next = sentinal.next.next;
            } else if (size == 1) {
                sentinal.next = sentinal;
                sentinal.prev = sentinal;
            }
            this.size--;
            return getItem;
        }
        return null;
    }

    public T removeLast() {
        if (sentinal.prev.item != null) {
            T getItem = sentinal.prev.item;
            if (size > 1) {
                sentinal.prev.prev.next = sentinal;
                sentinal.prev = sentinal.prev.prev;
            } if (size == 1) {
                sentinal.next = sentinal;
                sentinal.prev = sentinal;
            }
            this.size--;
            return getItem;
        }
        return null;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        } else {
            Node p = sentinal.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.item;
        }
    }


    public T getRecursive(int index) {
        Node p = sentinal;
        if (index < 0 || index > size - 1) {
            return null;
        }
        return itr(p, index);

    }

    private T itr(Node p, int index) {
        if (index < 0) {
            return p.item;
        }
        return itr(p.next, index - 1);
    }

}
