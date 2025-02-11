package com.teragrep.new_rlo_06.fragment.clocks;

import com.teragrep.new_rlo_06.fragment.Fragment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
        ByteBuffer inputBuffer = ByteBuffer.wrap("12345x".getBytes(StandardCharsets.US_ASCII));

        NumberSequenceClock numberSequenceClock = new NumberSequenceClock(5);

        Fragment numberFragment = numberSequenceClock.submit(inputBuffer);

        Assertions.assertFalse(numberFragment.isStub());
        Assertions.assertEquals("12345", numberFragment.toString());
        Assertions.assertEquals(12345, numberFragment.toInt());

        Assertions.assertTrue(inputBuffer.hasRemaining());
        Assertions.assertEquals((byte)'x', inputBuffer.get());
    }

    @Test
    public void testPreReadBuffer() {
        ByteBuffer inputBuffer = ByteBuffer.wrap("n54321x".getBytes(StandardCharsets.US_ASCII));

        inputBuffer.get(); // read one out

        NumberSequenceClock numberSequenceClock = new NumberSequenceClock(5);

        Fragment numberFragment = numberSequenceClock.submit(inputBuffer);

        Assertions.assertFalse(numberFragment.isStub());
        Assertions.assertEquals("54321", numberFragment.toString());
        Assertions.assertEquals(54321, numberFragment.toInt());

        Assertions.assertTrue(inputBuffer.hasRemaining());
        Assertions.assertEquals((byte)'x', inputBuffer.get());
    }

    @Disabled
    @Test
    public void testMultipleBuffers() {

    }

    @Test
    public void testLengthExceeded() {
        NumberSequenceClock numberSequenceClock = new NumberSequenceClock(1);
        ByteBuffer inputBuffer = ByteBuffer.wrap("12".getBytes(StandardCharsets.US_ASCII));
        Assertions.assertThrows(IllegalArgumentException.class, () -> numberSequenceClock.submit(inputBuffer));
    }
}
