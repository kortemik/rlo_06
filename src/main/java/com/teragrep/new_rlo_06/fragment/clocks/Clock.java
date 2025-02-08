package com.teragrep.new_rlo_06.fragment.clocks;

import java.nio.ByteBuffer;

public interface Clock<T> {
    T submit(ByteBuffer input);
}
