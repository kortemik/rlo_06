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
