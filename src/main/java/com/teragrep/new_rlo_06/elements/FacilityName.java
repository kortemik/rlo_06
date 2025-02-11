package com.teragrep.new_rlo_06.elements;

// TODO consider unifying FacilityImpl and this

public enum FacilityName implements Facility{
    KERN(0, "KERN"),
    USER(1, "USER"),
    MAIL(2, "MAIL"),
    DAEMON(3, "DAEMON"),
    AUTH(4, "AUTH"),
    SYSLOG(5, "SYSLOG"),
    LPR(6, "LPR"),
    NEWS(7, "NEWS"),
    UUCP(8, "UUCP"),
    CRON(9, "CRON"),
    AUTHPRIV(10, "AUTHPRIV"),
    FTP(11, "FTP"),
    NTP(12, "NTP"),
    AUDIT(13, "AUDIT"),
    ALERT(14, "ALERT"),
    CLOCK(15, "CLOCK"),
    LOCAL0(16, "LOCAL0"),
    LOCAL1(17, "LOCAL1"),
    LOCAL2(18, "LOCAL2"),
    LOCAL3(19, "LOCAL3"),
    LOCAL4(20, "LOCAL4"),
    LOCAL5(21, "LOCAL5"),
    LOCAL6(22, "LOCAL6"),
    LOCAL7(23, "LOCAL7");

    private final int code;
    private final String name;

    FacilityName(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String fromCode(int code) {
        for (FacilityName facilityName : FacilityName.values()) {
            if (facilityName.code == code) {
                return facilityName.name;
            }
        }
        throw new IllegalArgumentException("Invalid facility code <[" + code + "]>");
    }

    @Override
    public int value() {
        return code;
    }
}
