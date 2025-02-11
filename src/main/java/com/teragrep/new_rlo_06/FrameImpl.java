package com.teragrep.new_rlo_06;

import com.teragrep.new_rlo_06.elements.Message;
import com.teragrep.new_rlo_06.elements.Priority;

public class FrameImpl implements Frame{

    private final Priority priority;
    private final Message message;
    public FrameImpl(Priority priority, Message message) {
        this.priority = priority;
        this.message = message;
    }

    @Override
    public Priority priority() {
        return priority;
    }

    @Override
    public Message message() {
        return message;
    }

    @Override
    public boolean isStub() {
        return false;
    }
}
