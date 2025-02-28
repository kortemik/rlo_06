package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.elements.Lengthable;

import java.nio.ByteBuffer;

public class RejectingClock<E extends Lengthable>  {
    private final AlternativeStub<E> alternativeStub;
    private final Clock<E> clock;
    public RejectingClock(Clock<E> clock) {
        this.alternativeStub = new AlternativeStub<>();
        this.clock = clock;
    }


    public Alternative<E> submit(ByteBuffer input) {
        Alternative<E> rv = alternativeStub;
        try {
            E element = clock.submit(input);
            rv = new AlternativeImpl<>(element, input);
        }
        catch (IllegalArgumentException ignored) {

        }
        return rv;
    }
}
