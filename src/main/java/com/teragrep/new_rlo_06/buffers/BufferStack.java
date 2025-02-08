package com.teragrep.new_rlo_06.buffers;

import java.nio.ByteBuffer;
import java.util.Collection;

public interface BufferStack {
    void push(ByteBuffer buffer);

    ByteBuffer pop();

    void add(ByteBuffer buffer);

    void addAll(Collection<ByteBuffer> bufferCollection);

    void clear();

    void reclaim(ByteBuffer buffer);
}
