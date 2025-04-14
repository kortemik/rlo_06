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

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.ClockResult;
import com.teragrep.new_rlo_06.ClockResultFailed;
import com.teragrep.new_rlo_06.ClockResultImpl;
import com.teragrep.new_rlo_06.fragment.FragmentImpl;
import com.teragrep.new_rlo_06.fragment.FragmentStub;
import com.teragrep.new_rlo_06.fragment.Fragment;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ByteFragmentClock implements Clock<Fragment> {
    private static final ClockResultFailed<Fragment> failed = new ClockResultFailed<>();

    private static final FragmentStub fragmentStub = new FragmentStub();
    private final LinkedList<ByteBuffer> bufferSliceList;
    private static final int maximumLength = 1;
    private final byte requiredByte;

    public ByteFragmentClock(byte requiredByte) {
        this.bufferSliceList = new LinkedList<>();
        this.requiredByte = requiredByte;
    }

    public ClockResult<Fragment> submit(ClockResult<Fragment> previousResult, ByteBuffer input) {

        if (!previousResult.value().isStub()) {
            // already complete, so just more buffers are added

            List<ByteBuffer> moreBackingBuffers = new ArrayList<>(previousResult.buffers().size());
            moreBackingBuffers.addAll(previousResult.buffers());
            moreBackingBuffers.add(input);

            return new ClockResultImpl<>(previousResult.value(), moreBackingBuffers);
        }

        ClockResult<Fragment> rv;

        ByteBuffer slice = input.slice();
        int bytesRead = 0;
        boolean complete = false;
        while (input.hasRemaining()) {
            byte b = input.get();
            bytesRead++;

            if (b == requiredByte) {
                slice.limit(bytesRead);
                complete = true;
                break;
            }
            else {
                bufferSliceList.clear();
                return failed;
                // throw new IllegalArgumentException("invalid byte submited <[" + b + "]>");
            }
        }
        bufferSliceList.add(slice);

        Fragment fragment;
        if (complete) {
            fragment = new FragmentImpl(new LinkedList<>(bufferSliceList));
            bufferSliceList.clear();
        }
        else {
            fragment = fragmentStub;
        }
        rv = new ClockResultImpl<>(fragment,bufferSliceList);

        return rv;
    }
}
