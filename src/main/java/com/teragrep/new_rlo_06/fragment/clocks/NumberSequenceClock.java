package com.teragrep.new_rlo_06.fragment.clocks;

import com.teragrep.new_rlo_06.fragment.Fragment;
import com.teragrep.new_rlo_06.fragment.FragmentImpl;
import com.teragrep.new_rlo_06.fragment.FragmentStub;

import java.nio.ByteBuffer;
import java.util.LinkedList;

public class NumberSequenceClock implements Clock<Fragment> {
    private static final FragmentStub fragmentStub = new FragmentStub();

    private final LinkedList<ByteBuffer> bufferSliceList;
    private final int maximumLength;

    public NumberSequenceClock(int maximumLength) {
        this.bufferSliceList = new LinkedList<>();
        this.maximumLength = maximumLength;
    }

    public Fragment submit(ByteBuffer input) {

        ByteBuffer slice = input.slice();
        int bytesRead = 0;
        boolean complete = false;
        while (input.hasRemaining()) {
            byte b = input.get();
            bytesRead++;

            if (b < '0' || b > '9') {
                input.position(bytesRead - 1); // seek one backwards
                slice.limit(bytesRead - 1); // mask the non-number
                complete = true;
                break;
            }

            checkOverSize(bytesRead, bufferSliceList);
        }

        bufferSliceList.add(slice);

        Fragment fragment;
        if (complete) {
            fragment = new FragmentImpl(new LinkedList<>(bufferSliceList));
            bufferSliceList.clear();
        }
        else {
            fragment = fragmentStub;
        }

        return fragment;
    }

    private void checkOverSize(int bytesRead, LinkedList<ByteBuffer> bufferSliceList) {
        long currentLength = 0;
        for (ByteBuffer slice : bufferSliceList) {
            currentLength = currentLength + slice.limit();
        }

        currentLength = currentLength + bytesRead;
        if (currentLength > maximumLength) {
            throw new IllegalArgumentException("too many numbers, maximum allowed is <[" + maximumLength + "]>");
        }
    }
}
