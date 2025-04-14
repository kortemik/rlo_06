package com.teragrep.new_rlo_06.elements;

import com.teragrep.new_rlo_06.Stubable;

import java.time.ZonedDateTime;

public interface Timestamp extends Stubable, Nillable {
    ZonedDateTime zonedDateTime();
}
