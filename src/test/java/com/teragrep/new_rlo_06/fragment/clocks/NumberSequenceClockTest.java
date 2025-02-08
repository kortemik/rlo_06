package com.teragrep.new_rlo_06.fragment.clocks;

import com.teragrep.new_rlo_06.fragment.Fragment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class NumberSequenceClockTest {

    @Test
    public void testStub() {
        NumberSequenceClock numberSequenceClock = new NumberSequenceClock(1);
        Fragment numberFragment = numberSequenceClock.submit(ByteBuffer.allocateDirect(0));
        Assertions.assertTrue(numberFragment.isStub());
    }

    @Test
    public void testParse() {
        NumberSequenceClock numberSequenceClock = new NumberSequenceClock(5);
        Fragment numberFragment = numberSequenceClock.submit(ByteBuffer.wrap("12345x".getBytes(StandardCharsets.US_ASCII)));

        Assertions.assertFalse(numberFragment.isStub());
        Assertions.assertEquals("12345", numberFragment.toString());
        Assertions.assertEquals(12345, numberFragment.toInt());
    }

    @Test
    public void testLengthExceeded() {

    }
}
