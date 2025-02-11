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
package com.teragrep.new_rlo_06.elements;

// TODO consider unifying FacilityImpl and this

public enum FacilityName implements Facility {
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
