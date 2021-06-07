package com.malu.base.gift.constant;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:05
 */
public class ApplicationConstant {

    private ApplicationConstant() {
        throw new IllegalStateException("Constant class");
    }

    // Inject bean
    public static final String EXTEND = "extend";
    public static final String CONSUMER = "consumer";
    public static final String TECHNICIAN = "technician";
    public static final String ADMIN = "admin";
    public static final String VENDOR = "vendor";

    // Language default
    public static final String LANGUAGE_DEFAULT = "en";

    // prefix version
    public static final String PREFIX_VERSION = "v";

}
