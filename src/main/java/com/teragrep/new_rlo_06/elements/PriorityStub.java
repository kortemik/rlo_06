package com.teragrep.new_rlo_06.elements;



public class PriorityStub implements Priority {

    @Override
    public boolean isStub() {
        return true;
    }

    @Override
    public Severity severity() {
        throw new UnsupportedOperationException("Stub object does not implement severity()");
    }

    @Override
    public Facility facility() {
        throw new UnsupportedOperationException("Stub object does not implement facility()");
    }
}
