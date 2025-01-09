package com.teragrep.new_rlo_06.snub;

import java.util.ArrayList;
import java.util.List;

public class RecordParse implements Snubable {
    private final List<Snubable> alternatives;

    public RecordParse() {
        alternatives = new ArrayList<>();
    }

    @Override
    public boolean isSnubbed() {
        
    }
}
