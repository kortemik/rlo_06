package com.teragrep.new_rlo_06;

import com.teragrep.new_rlo_06.elements.Priority;
import com.teragrep.new_rlo_06.elements.PriorityStub;
import com.teragrep.new_rlo_06.elements.clocks.PriorityClock;

import java.nio.ByteBuffer;

/**
 * Encapsulates data from ByteBuffers into a Frame until there is a newline in the data
 */
public class FrameClock implements Clock<Frame>{
    private static final FrameStub frameStub = new FrameStub();

    private static final PriorityStub priorityStub = new PriorityStub();

    private Priority priority;

    public FrameClock() {
        this(new PriorityClock());
    }

    public FrameClock(PriorityClock priorityClock) {
        this.priority = priorityStub;
    }

    @Override
    public Frame submit(ByteBuffer input) {
        if (true) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        return frameStub;
    }
}
