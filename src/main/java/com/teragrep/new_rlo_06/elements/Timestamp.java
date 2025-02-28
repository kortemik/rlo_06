package com.teragrep.new_rlo_06.elements;

import com.teragrep.new_rlo_06.Stubable;
import com.teragrep.new_rlo_06.elements.clocks.Alternative;

import java.time.ZonedDateTime;

public interface Timestamp extends Stubable, Nillable, Alternative {
    ZonedDateTime zonedDateTime();
}
