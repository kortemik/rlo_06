package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.Stubable;

import java.nio.ByteBuffer;
import java.util.List;

public interface Chance<T extends Stubable> {
    boolean failed();
    List<ByteBuffer> buffers();
}
