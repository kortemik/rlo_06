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

import com.teragrep.rlp_01.pool.Pool;
import com.teragrep.rlp_01.pool.UnboundPool;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class InputStreamByteBufferPump {

    private final ByteBufferLeaseStub bufferLeaseStub = new ByteBufferLeaseStub();
    private final Pool<ByteArrayPoolable> bytePool;

    public InputStreamByteBufferPump() {
        this(new UnboundPool<>(() -> new ByteArrayPoolableImpl(new byte[256 * 1024]), new ByteArrayPoolableStub()));
    }

    public InputStreamByteBufferPump(Pool<ByteArrayPoolable> bytePool) {
        this.bytePool = bytePool;
    }

    public ByteBufferLease pump(InputStream inputStream) throws IOException {

        ByteArrayPoolable byteArrayPoolable = bytePool.get();

        byte[] bytes = byteArrayPoolable.bytes();

        int read = inputStream.read(bytes);
        if (read >= 0) {
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            buffer.flip().limit(read);
            return new ByteBufferLeaseImpl(bytePool, byteArrayPoolable, buffer);
        }
        else {
            // returning empty back
            bytePool.offer(byteArrayPoolable);
            return bufferLeaseStub;
        }
    }

    // input buffers are different than the content buffers but should all go back the same way, like .release() in net_01
    // should input be a decorated one and on close release?
    // so are the pools so are the buffers, net_01 buf solution to own project, supplier for array backed an perhaps that ugly newChannel(inputStrea) read() solution here in the pump and be done with the pumping?
    // who should track the '\n' should it be the pump?

    // use java21 and MemorySegment for abstraction of both cases and use net_01 ref counting solution

}
