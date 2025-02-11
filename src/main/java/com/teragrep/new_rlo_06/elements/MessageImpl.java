package com.teragrep.new_rlo_06.elements;

import com.teragrep.new_rlo_06.fragment.Fragment;

public class MessageImpl implements Message {

    public final Fragment messageFragment;

    public MessageImpl(Fragment messageFragment) {
        this.messageFragment = messageFragment;
    }

    @Override
    public String message() {
        return messageFragment.toString();
    }

    @Override
    public boolean isStub() {
        return false;
    }
}
