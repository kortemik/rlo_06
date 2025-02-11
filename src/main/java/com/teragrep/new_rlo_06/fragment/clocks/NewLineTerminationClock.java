package com.teragrep.new_rlo_06.fragment.clocks;

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.fragment.Fragment;
import com.teragrep.new_rlo_06.fragment.FragmentImpl;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class NewLineTerminationClock implements Clock<Fragment> {

    private final List<ByteBuffer> bufferSliceList;

    public NewLineTerminationClock() {
        this.bufferSliceList = new LinkedList<>();
    }

    @Override
    public Fragment submit(ByteBuffer input) {
        if (true) {
            throw new UnsupportedOperationException("not implemented yet");
        }
        ByteBuffer slice = input.slice();

        while (input.hasRemaining()) {
            input.get();
        }

        bufferSliceList.add(slice);

        return new FragmentImpl(bufferSliceList);
    }
}
