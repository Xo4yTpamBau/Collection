package by.bookstore.list;

import java.util.Iterator;
import java.util.ListIterator;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] objects;
    private int size;

    public ArrayList() {
        this.objects = new Object[INITIAL_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        this.objects = new Object[initialCapacity];
    }

    public ArrayList(List<T> list) {
        int size1 = list.size();
        this.objects = new Object[size1];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = list.get(i);
        }
        size = size1;
    }

    public static void main(String[] args) {
        List<String> myArrayList = new ArrayList<>();

        myArrayList.add("Hello 1");
        myArrayList.add("Hello 2");
        myArrayList.add("Hello 3");
        myArrayList.add("Hello 4");
        myArrayList.add("Hello 5");

        ListIterator<String> stringListIterator = myArrayList.listIterator();
        System.out.println(stringListIterator.next());
        System.out.println(stringListIterator.next());
        System.out.println(stringListIterator.previous());
        System.out.println(stringListIterator.previous());
        System.out.println(stringListIterator.previous());
    }

    private void grow() {
        int v = size + (size / 2);
        Object[] objects1 = new Object[v];

        for (int i = 0; i < objects.length; i++) {
            objects1[i] = objects[i];
        }
        this.objects = objects1;
    }

    @Override
    public boolean add(T o) {
        if (size == objects.length) grow();
        objects[size++] = o;
        return true;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > objects.length) throw new IllegalArgumentException();
        for (int i = index; i < objects.length - 1; i++) {
            objects[i] = objects[i + 1];
        }
        size--;
    }

    @Override
    public void remove(T o) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) break;
            if (objects[i].equals(o)) {
                for (int j = i; j < objects.length - 1; j++) {
                    objects[j] = objects[j + 1];
                }
                size--;
                break;
            }
        }
    }

    @Override
    public T get(int index) {
        return (T) objects[index];
    }

    @Override
    public T set(int index, T o) {
        Object object = objects[index];
        objects[index] = o;
        return (T) object;
    }

    @Override
    public void add(int index, T o) {
        if (size == objects.length) grow();
        if (objects.length - 1 - index >= 0)
            System.arraycopy(objects, index, objects, index + 1, objects.length - 1 - index);
        objects[index] = o;
        size++;
    }

    @Override
    public int indexOf(T o) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public boolean contains(T o) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(o))
                return true;
        }
        return false;
    }

    @Override
    public T[] toArray() {
        int count = 0;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                count++;

            }
        }
        Object[] objects1 = new Object[count];
        for (int i = 0; i < objects1.length; i++) {
            objects1[i] = objects[i];
        }
        return (T[]) objects1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < objects.length; i++) {
            objects[i] = null;
            size--;
        }
    }

    @Override
    public void trimToSize() {
        int count = 0;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                count++;

            }
        }

        Object[] objects1 = new Object[count];
        for (int i = 0; i < objects1.length; i++) {
            objects1[i] = objects[i];
        }
        this.objects = objects1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder v = new StringBuilder("[");
        for (int i = 0; i < objects.length; i++) {
            if (i < objects.length - 1) {
                v.append(objects[i].toString()).append(", ");
            } else v.append(objects[i].toString());
        }
        v.append("]");
        return v.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr(this.objects);
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(this.objects);
    }

    private class ListItr extends Itr implements ListIterator<T>{

        public ListItr(Object[] objects) {
            super(objects);
        }

        @Override
        public boolean hasPrevious() {
            if (cursor < 0) return false;
            return objects[--cursor] != null;
        }

        @Override
        public T previous() {
            return (T) objects[--cursor];
        }

        @Override
        public int nextIndex() {
            return cursor++;
        }

        @Override
        public int previousIndex() {
            return cursor--;
        }

        @Override
        public void set(T t) {
            objects[cursor] = t;

        }

        @Override
        public void add(T t) {
            if (size == objects.length) grow();
            for (int i = size; i < (size - cursor); i--) {
                objects[i + 1] = objects[i];
            }
            objects[cursor] = t;
        }
    }

    private class Itr implements Iterator<T>{

        private final Object[] objects;
        protected int cursor = -1;

        public Itr(Object[] objects) {
            this.objects = objects;
        }

        @Override
        public boolean hasNext() {
            return objects[cursor] != null;
        }

        @Override
        public T next() {
            return (T) objects[++cursor];
        }

        @Override
        public void remove() {
            objects[++cursor] = null;
        }
    }
}
