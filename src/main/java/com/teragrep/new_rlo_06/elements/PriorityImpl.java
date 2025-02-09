package com.teragrep.new_rlo_06.elements;

import com.teragrep.new_rlo_06.fragment.Fragment;

public class PriorityImpl implements Priority {

    private final Fragment openFragment;
    private final Fragment numberSequenceFragment;
    private final Fragment closeFragment;

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
    public int severity() {
        return numberSequenceFragment.toInt() & 7;
    }

    @Override
    public int facility() {
        return numberSequenceFragment.toInt() >> 3;
    }
}
