package com.teragrep.new_rlo_06.fragment.clocks;

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.fragment.FragmentImpl;
import com.teragrep.new_rlo_06.fragment.FragmentStub;
import com.teragrep.new_rlo_06.fragment.Fragment;


import java.nio.ByteBuffer;
import java.util.LinkedList;

public class ByteFragmentClock implements Clock<Fragment> {

    private static final FragmentStub fragmentStub = new FragmentStub();
    private final LinkedList<ByteBuffer> bufferSliceList;
    private static final int maximumLength = 1;
    private final byte requiredByte;


    public ByteFragmentClock(byte requiredByte) {
        this.bufferSliceList = new LinkedList<>();
        this.requiredByte = requiredByte;
    }

    public Fragment submit(ByteBuffer input) {

        ByteBuffer slice = input.slice();
        int bytesRead = 0;
        boolean complete = false;
        while (input.hasRemaining()) {
            byte b = input.get();
            bytesRead++;
            checkOverSize(bytesRead, bufferSliceList);
            if (b == requiredByte) {
                slice.limit(bytesRead);
                complete = true;
                break;
            }
            else {
                throw new IllegalArgumentException("invalid byte submited <[" + b + "]>");
            }
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
            throw new IllegalArgumentException("only one byte may be read with ByteFragmentClock");
        }
    }

}
