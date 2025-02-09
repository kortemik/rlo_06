package com.teragrep.new_rlo_06;

import com.teragrep.new_rlo_06.elements.Priority;
import com.teragrep.new_rlo_06.elements.PriorityStub;
import com.teragrep.new_rlo_06.elements.clocks.PriorityClock;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public class FrameClock implements Clock<Frame>{
    private static final FrameStub frameStub = new FrameStub();

    private final List<Priority> priority;

    public FrameClock() {
        this(new PriorityClock());
    }

    public FrameClock(PriorityClock priorityClock) {

        this.priority = Collections.singletonList(new PriorityStub());
    }

    @Override
    public Frame submit(ByteBuffer input) {
        return frameStub;
    }
}
