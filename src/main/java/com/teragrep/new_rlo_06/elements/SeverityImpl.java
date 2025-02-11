package com.teragrep.new_rlo_06.elements;

public class SeverityImpl implements Severity {

    private final int value;
    public SeverityImpl(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        if (value < 0 || value > 7) {
            throw new IllegalArgumentException("Invalid severity value <[" + value + "]>");
        }
        return value;
    }

    @Override
    public String toString() {
        return SeverityName.fromCode(value());
    }
}
