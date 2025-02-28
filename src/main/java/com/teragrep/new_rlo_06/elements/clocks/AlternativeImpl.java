package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.elements.Lengthable;

import java.nio.ByteBuffer;

public class AlternativeImpl<T extends Lengthable> implements Alternative<T> {
    private final T element;
    private final ByteBuffer lastBuffer;

    public AlternativeImpl(T element, ByteBuffer lastBuffer) {
        this.element = element;
        this.lastBuffer = lastBuffer;
    }

    @Override
    public T element() {
        return element;
    }

    @Override
    public ByteBuffer lastBuffer() {
        return lastBuffer;
    }

    @Override
    public boolean isStub() {
        return false;
    }
}
