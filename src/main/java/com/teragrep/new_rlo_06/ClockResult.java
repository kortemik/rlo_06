package com.teragrep.new_rlo_06;

import java.nio.ByteBuffer;
import java.util.List;

public interface ClockResult<R extends Stubable> {
    R value(); // actual value, stub or not
    List<ByteBuffer> buffers(); // if value is not stub, then copy this buffers and added to new ClockResult
    boolean failed(); // if fails return failed object, which just throws on other methods
}
