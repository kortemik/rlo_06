package com.teragrep.new_rlo_06.elements;

import com.teragrep.new_rlo_06.fragment.Fragment;

import java.time.ZonedDateTime;

public class TimestampImpl implements Timestamp {

    public TimestampImpl(Fragment fragment) {

    }

    @Override
    public ZonedDateTime zonedDateTime() {
        return null;
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
        return false;
    }
}
