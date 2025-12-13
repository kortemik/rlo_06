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
package com.teragrep.rlo_06;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

final class StreamImpl implements Stream {

    private final InputStreamByteBufferPump pump;

    private static final ByteBufferLease emptyBufferLease = new ByteBufferLeaseStub();
    private InputStream inputStream;
    private ByteBufferLease byteBufferLease;
    private int offset;
    private int length;
    private byte b;
    private byte[] buffer;

    StreamImpl() {
        this.byteBufferLease = emptyBufferLease;
        this.inputStream = new ByteArrayInputStream(new byte[0]);
        this.pump = new InputStreamByteBufferPump();
        this.offset = 0;
    }

    @Override
    public void setInputStream(InputStream inputStream) {
        this.byteBufferLease = emptyBufferLease;
        this.inputStream = inputStream;
        this.buffer = null;
        this.offset = 0;
    }

    @Override
    public Byte get() {
        //System.out.println("returning " + b);
        return b;
    }

    @Override
    public boolean next() {
        if (offset >= length) {
            try {
                byteBufferLease.close();
            }
            catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            try {
                byteBufferLease = pump.pump(inputStream);
                if (!byteBufferLease.isStub()) {
                    length = byteBufferLease.buffer().limit();
                    buffer = byteBufferLease.buffer().array();
                    offset = 0;
                }
                else {
                    offset = length + 1;
                }
            }
            catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        final boolean hasRemaining;
        if (offset >= length) {
            try {
                buffer = null;
                byteBufferLease.close(); // goes back to pool
                byteBufferLease = emptyBufferLease;
                offset = 0;
                length = 0;
            }
            catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            hasRemaining = false;
        }
        else {
            hasRemaining = true;

            //b = (byte) varHandle.get(memorySegment, offset);
            b = buffer[offset];
            offset++;
        }

        //System.out.println("next says " + hasRemaining + " cuz currentBuffer is " + currentBuffer);
        return hasRemaining;
    }
}
