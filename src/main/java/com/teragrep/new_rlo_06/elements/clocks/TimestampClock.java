package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.elements.Timestamp;
import com.teragrep.new_rlo_06.elements.TimestampImpl;
import com.teragrep.new_rlo_06.elements.TimestampNil;

import java.nio.ByteBuffer;

public class TimestampClock implements Clock<Timestamp> {
    private final TimestampNilChance timestampNilChance;
    private final TimestampChance timestampChance;

    private Timestamp timestampNil;
    private Timestamp timestampImpl;

    public TimestampClock() {
        this.timestampNilChance = new TimestampNilChance();
        this.timestampChance = new TimestampChance();
    }

    @Override
    public Timestamp submit(ByteBuffer input) {

        if (timestampNil.isStub()) {
            ByteBuffer duplicate = input.duplicate();
            timestampNil = timestampNilChance.submit(duplicate);
        }

        if (timestampImpl.isStub()) {
            ByteBuffer duplicate = input.duplicate();
            timestampImpl = timestampChance.submit(duplicate);
        }

        if (!timestampNil.isStub() && !timestampImpl.isStub()) {
            if (timestampNil.rejected() && timestampImpl.rejected()) {
                throw new IllegalStateException("no valid alternative");
            }

            if (!timestampNil.rejected() && !timestampImpl.rejected()) {
                throw new IllegalStateException("dubious alternatives");
            }

            if (timestampNil.rejected()) {

            }

            if (timestampImpl.rejected()) {

            }
        }
    }
}
