package com.teragrep.new_rlo_06.elements;

import com.teragrep.new_rlo_06.fragment.Fragment;

public class VersionImpl implements Version {

    private final Fragment versionFragment;
    public VersionImpl(Fragment versionFragment) {
        this.versionFragment = versionFragment;
    }
    @Override
    public int value() {
        return versionFragment.toInt();
    }

    @Override
    public boolean isStub() {
        return false;
    }
}
