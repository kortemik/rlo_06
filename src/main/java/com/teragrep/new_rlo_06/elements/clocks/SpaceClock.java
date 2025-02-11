package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.elements.Space;
import com.teragrep.new_rlo_06.elements.SpaceImpl;
import com.teragrep.new_rlo_06.elements.SpaceStub;
import com.teragrep.new_rlo_06.fragment.Fragment;
import com.teragrep.new_rlo_06.fragment.clocks.ByteFragmentClock;

import java.nio.ByteBuffer;

public class SpaceClock implements Clock<Space> {
    private static final SpaceStub spaceStub = new SpaceStub();
    private final ByteFragmentClock byteFragmentClock;

    public SpaceClock() {
        this.byteFragmentClock = new ByteFragmentClock((byte)' ');
    }

    @Override
    public Space submit(ByteBuffer input) {
        final Space space;

        Fragment spaceFragment = byteFragmentClock.submit(input);
        if (!spaceFragment.isStub()) {
            space = new SpaceImpl(spaceFragment);
        }
        else {
            space = spaceStub;
        }
         return space;
    }
}
