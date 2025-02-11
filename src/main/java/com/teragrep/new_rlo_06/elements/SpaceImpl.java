package com.teragrep.new_rlo_06.elements;

import com.teragrep.new_rlo_06.fragment.Fragment;

public class SpaceImpl implements Space {

    private final Fragment spaceFragment;

    public SpaceImpl(Fragment spaceFragment) {
        this.spaceFragment = spaceFragment;
    }

    @Override
    public boolean isStub() {
        return false;
    }
}
