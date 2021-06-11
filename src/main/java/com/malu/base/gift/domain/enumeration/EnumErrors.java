package com.malu.base.gift.domain.enumeration;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 04/06/2021
 * Time: 01:04
 */
public enum EnumErrors {
    GIFT_NOT_FOUND("Gift", "NotFound", ""),
    GIFT_EXISTS("Gift", "Exists", ""),
    GIFT_LANG_NOT_FOUND("GiftLang", "NotFound", ""),
    GIFT_DEFAULT_LANGUAGE_EMPTY("GiftDefaultLanguage", "Empty", ""),
    GIFT_SEASON_NOT_FOUND("GiftSeason", "NotFound", ""),
    GIFT_PROVIDER_NOT_FOUND("Gift", "NotFound", ""),
    ;
    // ... add more cases here ...
    private final String entityName;
    private final String errorKey;
    private String message;

    public String getMessage() {
        message = getEntityName() + "." + getErrorKey();
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    EnumErrors(String entityName, String errorKey, String message) {
        this.entityName = entityName;
        this.errorKey = errorKey;
        this.message = message;
    }
}
