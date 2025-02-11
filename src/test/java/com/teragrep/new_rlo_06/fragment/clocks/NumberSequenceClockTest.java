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
        Assertions.assertEquals((byte) 'x', inputBuffer.get());
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
        Assertions.assertEquals((byte) 'x', inputBuffer.get());
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
