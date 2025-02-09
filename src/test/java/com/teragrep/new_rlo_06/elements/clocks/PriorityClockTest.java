package com.teragrep.new_rlo_06.elements.clocks;

import com.teragrep.new_rlo_06.elements.Priority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class PriorityClockTest {

    @Test
    public void testPriorityClock() {
        PriorityClock clock = new PriorityClock();

        Priority priority = clock.submit(ByteBuffer.wrap("<".getBytes(StandardCharsets.US_ASCII)));
        Assertions.assertTrue(priority.isStub());

        priority = clock.submit(ByteBuffer.wrap("1".getBytes(StandardCharsets.US_ASCII)));
        Assertions.assertTrue(priority.isStub());

        priority = clock.submit(ByteBuffer.wrap("2".getBytes(StandardCharsets.US_ASCII)));
        Assertions.assertTrue(priority.isStub());

        priority = clock.submit(ByteBuffer.wrap("3".getBytes(StandardCharsets.US_ASCII)));
        Assertions.assertTrue(priority.isStub());

        priority = clock.submit(ByteBuffer.wrap(">".getBytes(StandardCharsets.US_ASCII)));
        Assertions.assertFalse(priority.isStub());

        Assertions.assertEquals(15, priority.facility()); // clock daemon
        Assertions.assertEquals(3, priority.severity()); // error

        priority = clock.submit(ByteBuffer.wrap("<".getBytes(StandardCharsets.US_ASCII)));
        Assertions.assertTrue(priority.isStub());
    }
}
