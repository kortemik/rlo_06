package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.Stubable;
import com.teragrep.new_rlo_06.elements.Lengthable;

import java.nio.ByteBuffer;

public interface Alternative<E extends Lengthable> extends Stubable {
    E element();

    ByteBuffer lastBuffer();
}
