package com.ericsson.eiffel.remrem.config;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class Base64Decryption {

    // To decrypt Base64 encode ldap manager password
    protected String decode(String password) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(password));
    }

}
