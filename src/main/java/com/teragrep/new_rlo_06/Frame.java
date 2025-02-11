package com.teragrep.new_rlo_06;

import com.teragrep.new_rlo_06.elements.Message;
import com.teragrep.new_rlo_06.elements.Priority;

public interface Frame extends Stubable {
    Priority priority();
    Message message();
}
