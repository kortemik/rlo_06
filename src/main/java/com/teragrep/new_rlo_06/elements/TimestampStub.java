package com.teragrep.new_rlo_06.elements;

import java.time.ZonedDateTime;

public class TimestampStub implements Timestamp {
    @Override
    public ZonedDateTime zonedDateTime() {
        throw new UnsupportedOperationException("Stub object does not implement zonedDateTime()");
    }

    @Override
    public boolean isNil() {
        throw new UnsupportedOperationException("Stub object does not implement isNil()");
    }

    @Override
    public boolean isStub() {
        return true;
    }
}
