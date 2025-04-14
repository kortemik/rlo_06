package com.teragrep.new_rlo_06;

import java.nio.ByteBuffer;
import java.util.List;

public class ClockResultImpl<R extends Stubable> implements ClockResult<R> {

    private final R value;
    private final List<ByteBuffer> backingBuffers;

    public ClockResultImpl(R value, List<ByteBuffer> backingBuffer) {
        this.value = value;
        this.backingBuffers = backingBuffers;
    }

    @Override
    public R value() {
        return value;
    }

    @Override
    public List<ByteBuffer> buffers() {
        return backingBuffers;
    }

    @Override
    public boolean failed() {
        return false;
    }
}
