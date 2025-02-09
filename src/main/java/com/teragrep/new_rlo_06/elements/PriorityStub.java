package com.teragrep.new_rlo_06.elements;



public class PriorityStub implements Priority {

    @Override
    public boolean isStub() {
        return true;
    }

    @Override
    public int severity() {
        throw new UnsupportedOperationException("Stub object does not implement severity()");
    }

    @Override
    public int facility() {
        throw new UnsupportedOperationException("Stub object does not implement facility()");
    }
}
