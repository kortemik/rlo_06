package com.teragrep.new_rlo_06.elements.loads;

import com.teragrep.new_rlo_06.Loadable;
import com.teragrep.new_rlo_06.elements.Message;
import com.teragrep.new_rlo_06.elements.MessageImpl;
import com.teragrep.new_rlo_06.fragment.FragmentImpl;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MessageLoad implements Loadable<Message> {
    private final List<ByteBuffer> bufferSliceList;
    public MessageLoad() {
        this.bufferSliceList = new LinkedList<>();
    }

    @Override
    public Message load(ByteBuffer[] byteBuffers) {
        for (ByteBuffer buffer : byteBuffers) {
            bufferSliceList.add(buffer.slice());
            buffer.position(buffer.limit());
        }
        Message message = new MessageImpl(new FragmentImpl(new ArrayList<>(bufferSliceList)));

        bufferSliceList.clear();
        return message;
    }
}
