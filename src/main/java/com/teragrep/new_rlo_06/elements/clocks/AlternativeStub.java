package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.elements.Lengthable;

import java.nio.ByteBuffer;

public class AlternativeStub<T extends Lengthable> implements Alternative<T> {
    @Override
    public T element() {
        throw new UnsupportedOperationException("AlternativeStub does not support element()");
    }

    @Override
    public ByteBuffer lastBuffer() {
        throw new UnsupportedOperationException("AlternativeStub does not support lastBuffer()");
    }

    @Override
    public boolean isStub() {
        return true;
    }
}
