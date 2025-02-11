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
package com.teragrep.new_rlo_06;

import com.teragrep.new_rlo_06.elements.*;
import com.teragrep.new_rlo_06.elements.clocks.PriorityClock;
import com.teragrep.new_rlo_06.elements.clocks.VersionClock;
import com.teragrep.new_rlo_06.elements.loads.MessageLoad;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Loads complete frame from ByteBuffers into a Frame. No termination check.
 */
public class FrameLoad implements Loadable<Frame> {

    private static final FrameStub frameStub = new FrameStub();
    private static final PriorityStub priorityStub = new PriorityStub();
    private static final VersionStub versionStub = new VersionStub();

    private final PriorityClock priorityClock = new PriorityClock();
    private final VersionClock versionClock = new VersionClock();
    private final MessageLoad messageLoad = new MessageLoad();

    @Override
    public Frame load(ByteBuffer[] byteBuffers) {
        Deque<ByteBuffer> buffers = new ArrayDeque<>(Arrays.asList(byteBuffers));

        Priority priority = priorityStub;
        while (priority.isStub()) {
            ByteBuffer input = buffers.pop();
            priority = priorityClock.submit(input);

            if (input.hasRemaining()) {
                buffers.push(input);
            }
        }

        Version version = versionStub;
        while (version.isStub()) {
            ByteBuffer input = buffers.pop();
            version = versionClock.submit(input);

            if (input.hasRemaining()) {
                buffers.push(input);
            }
        }


        // message loads them all
        Message message = messageLoad.load(buffers.toArray(new ByteBuffer[0]));

        return new FrameImpl(priority, version, message);
    }
}
