package com.teragrep.new_rlo_06;

import java.nio.ByteBuffer;
import java.util.List;

public class ClockResultFailed<R extends Stubable> implements ClockResult<R> {

    public ClockResultFailed() {

    }

    @Override
    public R value() {
        throw new UnsupportedOperationException("ClockResultFailed does not provide value");
    }

    @Override
    public List<ByteBuffer> buffers() {
        throw new UnsupportedOperationException("ClockResultFailed does not have backing buffers");
    }

    @Override
    public boolean failed() {
        return true;
    }
}
