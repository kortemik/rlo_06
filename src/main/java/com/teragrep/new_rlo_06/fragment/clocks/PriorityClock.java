package com.teragrep.new_rlo_06.fragment.clocks;

import com.teragrep.new_rlo_06.elements.Priority;
import com.teragrep.new_rlo_06.elements.PriorityImpl;
import com.teragrep.new_rlo_06.elements.PriorityStub;
import com.teragrep.new_rlo_06.fragment.Fragment;
import com.teragrep.new_rlo_06.fragment.FragmentStub;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public class PriorityClock implements Clock<Priority> {
    private static final FragmentStub fragmentStub = new FragmentStub();
    private static final PriorityStub priorityStub = new PriorityStub();

    private final ByteFragmentClock priorityOpenClock;
    private final NumberSequenceClock numberSequenceClock;
    private final ByteFragmentClock priorityCloseClock;

    private final List<Fragment> openFragment;
    private final List<Fragment> numberSequenceFragment;
    private final List<Fragment> closeFragment;


    public PriorityClock() {
        this.priorityOpenClock = new ByteFragmentClock((byte)'<');
        this.numberSequenceClock = new NumberSequenceClock(3);
        this.priorityCloseClock = new ByteFragmentClock((byte)'>');

        this.openFragment = Collections.singletonList(fragmentStub);
        this.numberSequenceFragment = Collections.singletonList(fragmentStub);
        this.closeFragment = Collections.singletonList(fragmentStub);
    }

    public Priority submit(ByteBuffer input) {
        Priority priority = priorityStub;
        if (openFragment.get(0).isStub()) {
            openFragment.set(0,priorityOpenClock.submit(input));
        }
        else if (numberSequenceFragment.get(0).isStub()) {
            numberSequenceFragment.set(0, numberSequenceClock.submit(input));
        }
        else if (closeFragment.get(0).isStub()) {
            closeFragment.set(0, priorityCloseClock.submit(input));
        }
        else {
            if (numberSequenceFragment.get(0).size() < 1) {
                throw new IllegalArgumentException("Priority must contain at least one number");
            }

            priority = new PriorityImpl(openFragment.get(0),  numberSequenceFragment.get(0), closeFragment.get(0));

            openFragment.set(0, fragmentStub);
            numberSequenceFragment.set(0, fragmentStub);
            closeFragment.set(0, fragmentStub);
        }
        return priority;
    }
}
