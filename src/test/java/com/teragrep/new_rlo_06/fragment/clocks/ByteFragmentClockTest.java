package com.teragrep.new_rlo_06.fragment.clocks;

import com.teragrep.new_rlo_06.fragment.Fragment;
import com.teragrep.new_rlo_06.fragment.Writeable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteFragmentClockTest {

    @Test
    public void testStub() {
        ByteFragmentClock byteFragmentClock = new ByteFragmentClock((byte) 'b');
        Fragment byteFragment = byteFragmentClock.submit(ByteBuffer.allocateDirect(0));
        Assertions.assertTrue(byteFragment.isStub());
    }

    @Test
    public void testParse() {
        ByteFragmentClock byteFragmentClock = new ByteFragmentClock((byte) '7');
        ByteBuffer inputBuffer = ByteBuffer.wrap("7".getBytes(StandardCharsets.UTF_8));
        Fragment byteFragment = byteFragmentClock.submit(inputBuffer);

        Assertions.assertFalse(byteFragment.isStub());
        Assertions.assertEquals("7", byteFragment.toString());
        Assertions.assertEquals(7, byteFragment.toInt());


        try (Writeable writeable = byteFragment.toWriteable()) {
            Assertions.assertTrue(writeable.hasRemaining());
            Assertions.assertEquals(1, writeable.buffers().length);
            ByteBuffer gettableByteBuffer = writeable.buffers()[0];
            Assertions.assertTrue(gettableByteBuffer.hasRemaining());
            Assertions.assertEquals((byte) '7', gettableByteBuffer.get());
        }

        // verify original is not modified by the writable access
        Assertions.assertEquals(7, byteFragment.toInt());
    }

    @Disabled
    @Test
    public void testMultipleBuffers() {

    }

    @Test
    public void testParseFail() {
        ByteFragmentClock byteFragmentClock = new ByteFragmentClock((byte) 'a');
        ByteBuffer inputBuffer = ByteBuffer.wrap("b".getBytes(StandardCharsets.UTF_8));

        Assertions.assertThrows(IllegalArgumentException.class, () -> byteFragmentClock.submit(inputBuffer));
    }
}
