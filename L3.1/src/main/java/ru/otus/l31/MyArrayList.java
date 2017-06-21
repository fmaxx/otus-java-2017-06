package ru.otus.l31;

import java.util.*;

/**
 * Created by maximfirsov on 20/06/2017.
 */
public class MyArrayList <T> implements List<T> {

    static private int SIZE = 5;

    int size;
    private Object[] array;

    public MyArrayList() {
        this(SIZE);
    }

    public MyArrayList(int capacity) {
        array = new Object[capacity];
        size = 0;
    }

    public MyArrayList(Collection<? extends T> c) {
        array = c.toArray();
        size = array.length;
    }

 // ------------------------------------------------------

    @Override
    public boolean add(T t) {
        int available = array.length - size;
        if(available < 1) {
            array = Arrays.copyOf(array, size + 1);
        }

        array[size++] = t;

        return true;
    }


    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] collectionArray = c.toArray();
        int length = collectionArray.length;
        int position = size;
        int available = array.length - size;

        if(length > available) {
            size = array.length + (length - available);
            array = Arrays.copyOf(array, size);
        }else{
            size += length;
        }

        System.arraycopy(collectionArray, 0, array, position, length);
        return length > 0;
    }



    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        T value = (T) array[index];
        array[index] = element;
        return value;
    }

    static <T> void copy(List<? super T> dest, List<? extends T> src) {
        dest.addAll(src);
    }


    static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);

    }

    @Override
    public void sort(Comparator<? super T> c) {
        ListIterator<T> i = this.listIterator();
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        for (Object ignored : a) {
            i.next();
        }
        array = a;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(0);
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyIterator<>(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new MyIterator<>(index);
    }




    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, this.size());
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    private class MyIterator<E> implements ListIterator<E> {

        int cursor;
        int last = -1;

        public MyIterator(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        public E next() {
            last = cursor;
            E result = null;
            if(cursor <= (size() - 1)){
                result = (E) array[cursor++];
            }
//            System.out.println("cursor : " +cursor + ", result: "+result);
            return result;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {
            MyArrayList.this.set(last, (T) e);
        }

        @Override
        public void add(E e) {

        }
    }



    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    // ------------------------------------------------------


    public boolean addAll(int index, Collection<? extends T> c) {
       return false;
    }




    @Override
    public boolean contains(Object o) {
        return false;
    }





    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }



    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }





    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }





    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }



    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
