/*
 * Java RFC524 parser library  RLO-06
 * Copyright (C) 2022  Suomen Kanuuna Oy
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
package com.teragrep.rlo_06.tests;

import com.teragrep.rlo_06.RFC5424Frame;
import com.teragrep.rlo_06.SDVector;
import com.teragrep.rlo_06.StreamableCachedInputStream;
import com.teragrep.rlo_06.StreamableIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamableIteratorTest {


    @Test
    void testNewParser() throws Exception {
        String SYSLOG_MESSAGE = "<14>1 2014-06-20T09:14:07.123456+00:00 host01 systemd DEA MSG-01 [ID_A@1 u=\"\\\"3\" e=\"t\"][ID_B@2 n=\"9\"] sigsegv\n";

        byte[] messageBytes = SYSLOG_MESSAGE.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.allocateDirect(messageBytes.length);
        buffer.put(messageBytes);
        buffer.flip();

        ByteBufferIterator byteBufferIterator = new ByteBufferIterator(buffer);

        StreamableIterator streamableIterator = new StreamableIterator(byteBufferIterator);

        RFC5424Frame rfc5424Frame = new RFC5424Frame(streamableIterator, true);

        assertTrue(rfc5424Frame.next());
        
        Assertions.assertEquals("14", rfc5424Frame.priority.toString());
        Assertions.assertEquals("1", rfc5424Frame.version.toString());
        Assertions.assertEquals("2014-06-20T09:14:07.123456+00:00", rfc5424Frame.timestamp.toString());
        Assertions.assertEquals("host01", rfc5424Frame.hostname.toString());
        Assertions.assertEquals("systemd", rfc5424Frame.appName.toString());
        Assertions.assertEquals("DEA", rfc5424Frame.procId.toString());
        Assertions.assertEquals("MSG-01", rfc5424Frame.msgId.toString());
        Assertions.assertEquals("sigsegv", rfc5424Frame.msg.toString());

        SDVector sdVector = new SDVector("ID_A@1", "u");
        // Structured Data 1
        Assertions.assertEquals("\\\"3", rfc5424Frame.structuredData.getValue(sdVector).toString());

        Assertions.assertFalse(rfc5424Frame.next());

        // Finished
        Assertions.assertThrows(IllegalStateException.class, rfc5424Frame.priority::toString);
        Assertions.assertThrows(IllegalStateException.class, rfc5424Frame.version::toString);
        Assertions.assertThrows(IllegalStateException.class, rfc5424Frame.timestamp::toString);
        Assertions.assertThrows(IllegalStateException.class, rfc5424Frame.hostname::toString);
        Assertions.assertThrows(IllegalStateException.class, rfc5424Frame.appName::toString);
        Assertions.assertThrows(IllegalStateException.class, rfc5424Frame.procId::toString);
        Assertions.assertThrows(IllegalStateException.class, rfc5424Frame.msgId::toString);
        Assertions.assertThrows(IllegalStateException.class, rfc5424Frame.msg::toString);

        // Structured Data Finished
        Assertions.assertThrows(IllegalStateException.class, () -> {
            rfc5424Frame.structuredData.getValue(sdVector);
        });
    }

    static class ByteBufferIterator implements Iterator<Byte> {

        private final ByteBuffer buffer;
        ByteBufferIterator(ByteBuffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public boolean hasNext() {
            return buffer.hasRemaining();
        }

        @Override
        public Byte next() {
            return buffer.get();
        }
    }
}
