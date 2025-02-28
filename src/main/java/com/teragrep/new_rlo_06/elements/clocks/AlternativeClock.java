package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.elements.Lengthable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlternativeClock<E extends Lengthable> implements Clock<E> {

    private final Map<RejectingClock<E>, Alternative<E>> clockToElement;
    private final E stub;


    // perhaps just List<Clock<E>> with a stub and construct the whole thing here
    public AlternativeClock(Map<RejectingClock<E>, Alternative<E>> clockToElement, E stub) {
        this.clockToElement = clockToElement;
        this.stub = stub;
    }

    @Override
    public E submit(ByteBuffer input) {

        for (Map.Entry<RejectingClock<E>, Alternative<E>> entry : clockToElement.entrySet()) {
            Alternative<E> alternative = entry.getValue();

            if (!alternative.isStub()) { // rejected, perhaps rename as blocked?
                if (alternative.element().isStub()) { // incomplete
                    entry.setValue(entry.getKey().submit(input));
                }
            }
        }

        E rv = stub;

        for () {
            // iterate over results, take the longest
        }

        // if found clear the map with new AlternativeImpl<stubs> and return the E

        // if all blocked, illegal argument exception, over and out


        return stub;
    }
}
