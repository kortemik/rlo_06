/*
 * Teragrep RFC5424 frame library for Java (rlo_06)
 * Copyright (C) 2022-2024 Suomen Kanuuna Oy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *
 * Additional permission under GNU Affero General Public License version 3
 * section 7
 *
 * If you modify this Program, or any covered work, by linking or combining it
 * with other code, such other code is not for that reason alone subject to any
 * of the requirements of the GNU Affero GPL version 3 as long as this Program
 * is the same Program as licensed from Suomen Kanuuna Oy without any additional
 * modifications.
 *
 * Supplemented terms under GNU Affero General Public License version 3
 * section 7
 *
 * Origin of the software must be attributed to Suomen Kanuuna Oy. Any modified
 * versions must be marked as "Modified version of" The Program.
 *
 * Names of the licensors and authors may not be used for publicity purposes.
 *
 * No rights are granted for use of trade names, trademarks, or service marks
 * which are in The Program if any.
 *
 * Licensee must indemnify licensors and authors for any liability that these
 * contractual assumptions impose on licensors and authors.
 *
 * To the extent this program is licensed as part of the Commercial versions of
 * Teragrep, the applicable Commercial License may apply to this file if you as
 * a licensee so wish it.
 */
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
