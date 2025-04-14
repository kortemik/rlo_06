package com.teragrep.new_rlo_06.elements;

import com.teragrep.new_rlo_06.fragment.Fragment;

import java.nio.ByteBuffer;
import java.time.ZonedDateTime;
import java.util.List;

public class TimestampNil implements Timestamp {

    private final Fragment dashFragment;

    public TimestampNil(Fragment dashFragment) {
        this.dashFragment = dashFragment;
    }

    @Override
    public boolean isNil() {
        return true;
    }

    @Override
    public ZonedDateTime zonedDateTime() {
        throw new UnsupportedOperationException("Nil timestamp does not have a zonedDateTime");
    }

    @Override
    public boolean isStub() {
        return false;
    }
}
