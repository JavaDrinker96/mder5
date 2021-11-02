package com.company.utils;

import com.company.exceptions.EmptyComparisonException;

import java.util.Locale;

public class StringUtil {

    public String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            sb.append(String.format("%02X", b[i] & 0xFF));
        }
        return sb.toString();
    }

    public boolean caseInsensitiveCompare(String firstString, String secondString) throws EmptyComparisonException {
        if (firstString == null || firstString.isEmpty()
                || secondString == null || secondString.isEmpty()) {
            throw new EmptyComparisonException("Error of comparison: empty or null fields");
        }

        return firstString.toLowerCase(Locale.ROOT).equals(secondString.toLowerCase(Locale.ROOT));
    }
}
