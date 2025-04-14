package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.ClockResult;
import com.teragrep.new_rlo_06.ClockResultFailed;
import com.teragrep.new_rlo_06.elements.Message;
import com.teragrep.new_rlo_06.elements.Timestamp;
import com.teragrep.new_rlo_06.elements.TimestampNil;
import com.teragrep.new_rlo_06.elements.TimestampStub;
import com.teragrep.new_rlo_06.fragment.Fragment;
import com.teragrep.new_rlo_06.fragment.clocks.ByteFragmentClock;

import java.nio.ByteBuffer;

public class TimestampNilClock implements Clock<Timestamp> {
    private static final ClockResultFailed<Timestamp> failed = new ClockResultFailed<>();
    private static final TimestampStub timestampStub = new TimestampStub();
    private final ByteFragmentClock byteFragmentClock;

    public TimestampNilClock() {
        this.byteFragmentClock = new ByteFragmentClock((byte) '-');
    }

    @Override
    public ClockResult<Timestamp> submit(ClockResult<Timestamp> previousResult, ByteBuffer input) {
        Timestamp timestamp = timestampStub;

        Fragment dashFragment = byteFragmentClock.submit(input);
        if (!dashFragment.isStub()) {
            timestamp = new TimestampNil(dashFragment);
        }

        return failed;
    }

}
