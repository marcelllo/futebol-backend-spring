package com.marcelo.futebol.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Util {

    public String generateUuid() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Z"));
        return this.md5(now.toString());
    }

    public String md5(String text) {

        return null;
    }
}
