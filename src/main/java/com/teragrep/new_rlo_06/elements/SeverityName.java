package com.teragrep.new_rlo_06.elements;

// TODO consider unifying SeverityImpl and this

public enum SeverityName implements Severity {
    EMERGENCY(0, "EMERGENCY"),
    ALERT(1, "ALERT"),
    CRITICAL(2, "CRITICAL"),
    ERROR(3, "ERROR"),
    WARNING(4, "WARNING"),
    NOTICE(5, "NOTICE"),
    INFORMATIONAL(6, "INFORMATIONAL"),
    DEBUG(7, "DEBUG");

    private final int code;
    private final String name;

    SeverityName(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String fromCode(int code) {
        for (SeverityName severityName : SeverityName.values()) {
            if (severityName.code == code) {
                return severityName.name;
            }
        }
        throw new IllegalArgumentException("Invalid severity code <[" + code + "]>");
    }

    @Override
    public int value() {
        return code;
    }
}
