package com.teragrep.rlo_06;

import java.util.Iterator;
import java.util.LinkedList;

public class StreamableIterator<E> implements Streamable<E> {

    private Iterator<E> iterator;

    private final LinkedList<E> elementList;

    public StreamableIterator() {
        this(new IteratorStub<>());
    }

    public StreamableIterator(Iterator<E> iterator) {
        this.iterator = iterator;
        this.elementList = new LinkedList<E>();
    }

    @Override
    public E get() {
        return elementList.peek();
    }

    @Override
    public boolean next() {
        boolean rv = iterator.hasNext();
        if (rv) {
            elementList.clear();
            elementList.push(iterator.next());
        }
        return rv;
    }

    public void setIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    private static class IteratorStub<E> implements Iterator<E> {

        @Override
        public boolean hasNext() {
            throw new IllegalStateException("IteratorStub does not implement hasNext");
        }

        @Override
        public E next() {
            throw new IllegalStateException("IteratorStub does not implement next");
        }
    }
}
