package com.teragrep.new_rlo_06.buffers;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public final class BufferStackDequeImpl implements BufferStack {
    private final Deque<ByteBuffer> buffers;
    public BufferStackDequeImpl() {
        this(new ArrayDeque<>());
    }

    public BufferStackDequeImpl(Collection<ByteBuffer> bufferCollection) {
        this.buffers = new ArrayDeque<>(bufferCollection);
    }

    @Override
    public void push(ByteBuffer buffer) {
        buffers.push(buffer);
    }

    @Override
    public ByteBuffer pop() {
        return buffers.pop();
    }

    @Override
    public void add(ByteBuffer buffer) {
        buffers.add(buffer);
    }

    @Override
    public void addAll(Collection<ByteBuffer> bufferCollection) {
        buffers.addAll(bufferCollection);
    }

    @Override
    public void clear() {
        buffers.clear();
    }

    @Override
    public void reclaim(ByteBuffer buffer) {
        // no-op
    }
}
