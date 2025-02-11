package com.teragrep.new_rlo_06.elements;

public class MessageStub implements Message {
    @Override
    public String message() {
        throw new UnsupportedOperationException("Stub object does not support message()");
    }

    @Override
    public boolean isStub() {
        return true;
    }
}
