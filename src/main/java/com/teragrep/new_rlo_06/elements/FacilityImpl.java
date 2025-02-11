package com.teragrep.new_rlo_06.elements;

public class FacilityImpl implements Facility {

    private final int value;
    public FacilityImpl(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        if (value < 0 || value > 23) {
            throw new IllegalArgumentException("Invalid facility value <[" + value + "]>");
        }
        return value;
    }

    @Override
    public String toString() {
        return FacilityName.fromCode(value());
    }
}
