package com.teragrep.new_rlo_06.elements;

import com.teragrep.new_rlo_06.Stubable;

public interface Priority extends Stubable {
    int severity();

    int facility();
}
