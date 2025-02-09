package com.teragrep.new_rlo_06;

import com.teragrep.new_rlo_06.elements.Priority;

public class FrameStub implements Frame {
    @Override
    public Priority priority() {
        throw new UnsupportedOperationException("Stub object does not implement priority()");
    }

    @Override
    public boolean isStub() {
        return true;
    }
}
