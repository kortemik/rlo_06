package com.teragrep.new_rlo_06;

import com.teragrep.new_rlo_06.elements.Message;
import com.teragrep.new_rlo_06.elements.Priority;
import com.teragrep.new_rlo_06.elements.PriorityStub;
import com.teragrep.new_rlo_06.elements.clocks.PriorityClock;
import com.teragrep.new_rlo_06.elements.loads.MessageLoad;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Loads complete frame from ByteBuffers into a Frame. No termination check.
 */
public class FrameLoad implements Loadable<Frame> {
    private static final FrameStub frameStub = new FrameStub();
    private static final PriorityStub priorityStub = new PriorityStub();

    private final PriorityClock priorityClock = new PriorityClock();
    private final MessageLoad messageLoad = new MessageLoad();

    @Override
    public Frame load(ByteBuffer[] byteBuffers) {
        Deque<ByteBuffer> buffers = new ArrayDeque<>(Arrays.asList(byteBuffers));

        Priority priority = priorityStub;
        while (priority.isStub()) {
            ByteBuffer input = buffers.pop();
            priority = priorityClock.submit(input);

            if (input.hasRemaining()) {
                buffers.push(input);
            }
        }

        // message loads them all
        Message message = messageLoad.load(buffers.toArray(new ByteBuffer[0]));

        return new FrameImpl(priority, message);
    }
}
