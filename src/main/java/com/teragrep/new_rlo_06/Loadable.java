package com.teragrep.new_rlo_06;

import java.nio.ByteBuffer;

public interface Loadable<T> {
    public T load(ByteBuffer[] byteBuffers);
}
