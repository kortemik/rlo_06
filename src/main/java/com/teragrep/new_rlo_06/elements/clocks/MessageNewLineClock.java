package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.elements.Message;
import com.teragrep.new_rlo_06.elements.MessageStub;
import com.teragrep.new_rlo_06.fragment.FragmentStub;
import com.teragrep.new_rlo_06.fragment.Fragment;
import com.teragrep.new_rlo_06.fragment.clocks.NewLineTerminationClock;

import java.nio.ByteBuffer;

public class MessageNewLineClock implements Clock<Message> {
    private static final MessageStub messageStub = new MessageStub();
    private static final FragmentStub fragmentStub = new FragmentStub();

    private final NewLineTerminationClock newLineTerminationClock;
    private Fragment messageFragment;

    public MessageNewLineClock() {
        this.newLineTerminationClock = new NewLineTerminationClock();
        this.messageFragment = fragmentStub;
    }

    @Override
    public Message submit(ByteBuffer input) {
        return messageStub;
    }
}
