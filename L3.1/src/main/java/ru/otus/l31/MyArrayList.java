package ru.otus.l31;

import java.util.*;

/**
 * Created by maximfirsov on 20/06/2017.
 */
public class MyArrayList <T> implements List<T> {

    static private int SIZE = 10;

    private int size = 0;
    private Object[] array;

    public MyArrayList() {
        array = new Object[SIZE];
    }

 // ------------------------------------------------------
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

    static <T> void copy(List<? super T> dest, List<? extends T> src) {
        dest.addAll(src);
    }


    static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);

    }

    @Override
    public void sort(Comparator<? super T> c) {
        ListIterator<T> i = this.listIterator();
        Arrays.sort(array, (Comparator) c);
        for (Object e : array) {
            i.next();
            i.set((T) e);
        }
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
        return Arrays.copyOf(array, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
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
            return cursor != size;
        }

        @Override
        public E next() {
            last = cursor;
            if(cursor < (size - 1)){
                return (E) array[++cursor];
            }
            return null;
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
            if(last > -1){
                MyArrayList.this.set(last, (T) e);
            }
        }

        @Override
        public void add(E e) {

        }
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
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
    public boolean add(T t) {
        return false;
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
    public T get(int index) {
        return null;
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
