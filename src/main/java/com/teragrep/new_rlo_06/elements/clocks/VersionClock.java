package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.elements.Version;
import com.teragrep.new_rlo_06.elements.VersionImpl;
import com.teragrep.new_rlo_06.elements.VersionStub;
import com.teragrep.new_rlo_06.fragment.Fragment;
import com.teragrep.new_rlo_06.fragment.clocks.ByteFragmentClock;

import java.nio.ByteBuffer;

public class VersionClock implements Clock<Version> {
    private static final VersionStub versionStub = new VersionStub();

    private final ByteFragmentClock versionFragmentClock;

    public VersionClock() {
        this.versionFragmentClock = new ByteFragmentClock((byte) '1');
    }


    @Override
    public Version submit(ByteBuffer input) {
        Fragment versionFragment = versionFragmentClock.submit(input);

        final Version version;
        if (!versionFragment.isStub()) {
            version = new VersionImpl(versionFragment);
        }
        else {
            version = versionStub;
        }
        return version;
    }
}
