package com.teragrep.rlo_06;

import java.util.Iterator;

final public class StreamableIterator implements Streamable<Byte> {

    private Iterator<Byte> iterator;

    private byte b;

    public StreamableIterator() {
        this(new IteratorStub<>());
    }

    public StreamableIterator(Iterator<Byte> iterator) {
        this.iterator = iterator;
    }

    @Override
    public Byte get() {
        return b;
    }

    @Override
    public boolean next() {
        boolean rv = iterator.hasNext();
        if (rv) {
            b = iterator.next();
        }
        return rv;
    }

    public void setIterator(Iterator<Byte> iterator) {
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
