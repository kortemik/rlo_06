package com.teragrep.new_rlo_06.elements;

import java.nio.ByteBuffer;
import java.time.ZonedDateTime;
import java.util.List;

public class TimestampRejected implements Timestamp {

    private final List<ByteBuffer> redoBuffers;
    public TimestampRejected(List<ByteBuffer> redoBuffers) {
        this.redoBuffers = redoBuffers;
    }

    @Override
    public ZonedDateTime zonedDateTime() {
        throw new UnsupportedOperationException("Rejected object does not implement zonedDateTime()");
    }

    @Override
    public boolean isStub() {
        return false;
    }

    @Override
    public boolean isNil() {
        return false;
    }

    @Override
    public boolean rejected() {
        return true;
    }
}
