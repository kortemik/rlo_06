package com.teragrep.new_rlo_06.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Disabled
    @Test
    public void testPriority() {

    }

    @Test
    public void testFacilitySeverityCtor() {
        Priority priority = new PriorityImpl(FacilityName.CLOCK, SeverityName.ERROR);
        Assertions.assertEquals(priority.facility().value(), 15);
        Assertions.assertEquals(priority.severity().value(), 3);
    }
}
