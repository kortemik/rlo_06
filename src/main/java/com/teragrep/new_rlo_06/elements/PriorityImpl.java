package com.teragrep.new_rlo_06.elements;

import com.teragrep.new_rlo_06.fragment.Fragment;
import com.teragrep.new_rlo_06.fragment.FragmentImpl;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class PriorityImpl implements Priority {

    private final Fragment openFragment;
    private final Fragment numberSequenceFragment;
    private final Fragment closeFragment;

    public PriorityImpl(Facility facility, Severity severity) {
        this(new FragmentImpl(Collections.singletonList(ByteBuffer.wrap(new byte[]{'<'}))), new FragmentImpl(Collections.singletonList(ByteBuffer.wrap(String.valueOf(facility.value() * 8 + severity.value()).getBytes(StandardCharsets.US_ASCII)))), new FragmentImpl(Collections.singletonList(ByteBuffer.wrap(new byte[]{'>'}))));
    }

    public PriorityImpl(Fragment openFragment, Fragment numberSequenceFragment, Fragment closeFragment) {
        this.openFragment = openFragment;
        this.numberSequenceFragment = numberSequenceFragment;
        this.closeFragment = closeFragment;
    }

    @Override
    public boolean isStub() {
        return false;
    }

    @Override
    public Severity severity() {
        return new SeverityImpl(numberSequenceFragment.toInt() & 7);
    }

    @Override
    public Facility facility() {
        return new FacilityImpl(numberSequenceFragment.toInt() >> 3);
    }
}
