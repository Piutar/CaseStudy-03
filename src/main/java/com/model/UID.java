package com.model;

import java.util.UUID;

public class UID {
    public static String getRandomID() {
        UUID uid = UUID.randomUUID();
        return uid.toString();
    }
}
