package com.teragrep.rlo_06;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public final class MsgId implements Consumer<Stream>, Clearable {
    /*
                                                             |||||||
                                                             vvvvvvv
    <14>1 2014-06-20T09:14:07.12345+00:00 host01 systemd DEA MSG-01 [ID_A@1 u="3" e="t"][ID_B@2 n="9"] sigsegv\n

    Actions: ______O
    Payload:'MSG-01 '
    States : ......T
    */
    private final ByteBuffer MSGID;
    MsgId() {
        this.MSGID = ByteBuffer.allocateDirect(32);
    }

    @Override
    public void accept(Stream stream) {
        byte b;
        short msgid_max_left = 32;

        if (!stream.next()) {
            throw new ParseException("Expected MSGID, received nothing");
        }
        b = stream.get();
        while (msgid_max_left > 0 && b != 32) {
            MSGID.put(b);
            msgid_max_left--;

            if (!stream.next()) {
                throw new ParseException("MSGID is too short, can't continue");
            }
            b = stream.get();
        }

        if (b != 32) {
            throw new MsgIdParseException("SP missing after MSGID or MSGID too long");
        }
    }

    @Override
    public void clear() {
        MSGID.clear();
    }

    @Override
    public String toString() {
        MSGID.flip();
        return StandardCharsets.US_ASCII.decode(MSGID).toString();
    }
}
