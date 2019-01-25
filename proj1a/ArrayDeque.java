public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int RFACTOR = 2;

    public ArrayDeque() {
        this.size = 0;
        this.items = (T[]) new Object[8];
    }

    public void addFirst(T item) {
        resize(items.length + 1, 0, 1);
        items[0] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFACTOR, 0, 0);
        }
        items[size] = item;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = 0; i <size; i++) {
            System.out.println(items[i]);
        }
    }

    public T removeFirst() {
        T a = items[0];
        items[0] = null;
        size--;
        resize(size, 1, 0);

        return a;
    }

    public T removeLast() {
        T a = items[size - 1];
        items[size - 1] = null;
        size = size - 1;

        return a;
    }

    public T get(int index) {
       if (index > size - 1) {
           return null;
       }
       return items[index];
    }

    private void resize(int capacity, int copyStart, int pasteStart) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, copyStart, a, pasteStart, size);
        items = a;
    }

    /*
    public static void main(String[] args) {
        ArrayDeque<Integer> deck = new ArrayDeque<>();
        deck.addLast(5);
        deck.addLast(6);
        deck.addLast(7);
        deck.addLast(8);
        deck.addFirst(4);
        deck.addLast(9);
        deck.addLast(10);
        deck.addLast(11);
        deck.addLast(12);
        deck.addLast(13);
        deck.printDeque();
        System.out.println();
        deck.removeLast();
        deck.printDeque();
        System.out.println();
        deck.removeFirst();
        deck.printDeque();
    }*/
}
