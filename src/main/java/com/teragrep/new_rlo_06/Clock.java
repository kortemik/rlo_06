package com.teragrep.new_rlo_06;

import java.nio.ByteBuffer;

public interface Clock<T> {
    T submit(ByteBuffer input);
}
