package com.malu.base.gift.admin.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.enumeration.EnumGiftStatus;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A DTO for the {@link com.malu.base.gift.domain.Gift} entity.
 */

@Data
public class GiftAdminVM implements Serializable {

    private Long id;

    private String hashCode;

    private String code;

    private String serial;

    private String name;

    private String description;

    private String icon;

    private String mediaPath;

    private BigDecimal originalPrice;

    private BigDecimal price;

    private Instant publishDate;

    private Instant startDate;

    private Instant expireDate;

    private String useGuide;

    private String terms;

    private String tags;

    private EnumGiftStatus status;

    private String userId;

    private List<GiftLangAdminVM> languages = new ArrayList<>();

    @JsonIgnore
    private GiftSeasonAdminVM giftSeason;

    @JsonIgnore
    private String langCode;

    private Instant createdDate;

    private Instant lastModifiedDate;

    public void setLangCode(String langCode) {
        Optional<GiftLangAdminVM> defaultLanguageLocal = languages.stream()
            .filter(lang -> langCode.equals(lang.getLangCode())).findFirst();
        if (defaultLanguageLocal.isPresent()) {
            name = defaultLanguageLocal.get().getName();
        } else {
            Optional<GiftLangAdminVM> optLanguageDefault = languages.stream()
                .filter(lang -> langCode.equals(ApplicationConstant.LANGUAGE_DEFAULT)).findFirst();
            optLanguageDefault.ifPresent(giftLangAdminVM -> name = giftLangAdminVM.getName());
        }
        this.langCode = langCode;
    }
}
