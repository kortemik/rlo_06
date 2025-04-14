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

import com.teragrep.new_rlo_06.Clock;
import com.teragrep.new_rlo_06.ClockResult;
import com.teragrep.new_rlo_06.elements.Message;
import com.teragrep.new_rlo_06.elements.Priority;
import com.teragrep.new_rlo_06.elements.PriorityImpl;
import com.teragrep.new_rlo_06.elements.PriorityStub;
import com.teragrep.new_rlo_06.fragment.Fragment;
import com.teragrep.new_rlo_06.fragment.FragmentStub;
import com.teragrep.new_rlo_06.fragment.clocks.ByteFragmentClock;
import com.teragrep.new_rlo_06.fragment.clocks.NumberSequenceClock;

import java.nio.ByteBuffer;

public class PriorityClock implements Clock<Priority> {

    private static final FragmentStub fragmentStub = new FragmentStub();
    private static final PriorityStub priorityStub = new PriorityStub();

    private final ByteFragmentClock priorityOpenClock;
    private final NumberSequenceClock numberSequenceClock;
    private final ByteFragmentClock priorityCloseClock;

    private Fragment openFragment;
    private Fragment numberSequenceFragment;
    private Fragment closeFragment;

    public PriorityClock() {
        this.priorityOpenClock = new ByteFragmentClock((byte) '<');
        this.numberSequenceClock = new NumberSequenceClock(3);
        this.priorityCloseClock = new ByteFragmentClock((byte) '>');

        this.openFragment = fragmentStub;
        this.numberSequenceFragment = fragmentStub;
        this.closeFragment = fragmentStub;
    }

    public ClockResult<Priority> submit(ClockResult<Priority> previousResult, ByteBuffer input) {
        Priority priority = priorityStub;
        if (openFragment.isStub()) {
            openFragment = priorityOpenClock.submit(input);
        }

        if (numberSequenceFragment.isStub()) {
            numberSequenceFragment = numberSequenceClock.submit(input);
        }

        if (closeFragment.isStub()) {
            closeFragment = priorityCloseClock.submit(input);
        }

        if (!closeFragment.isStub()) {
            if (numberSequenceFragment.size() < 1) {
                throw new IllegalArgumentException("Priority must contain at least one number");
            }

            priority = new PriorityImpl(openFragment, numberSequenceFragment, closeFragment);

            openFragment = fragmentStub;
            numberSequenceFragment = fragmentStub;
            closeFragment = fragmentStub;
        }
        return priority;
    }
}
