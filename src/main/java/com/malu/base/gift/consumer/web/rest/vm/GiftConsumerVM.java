package com.malu.base.gift.consumer.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.enumeration.EnumGiftStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Data;

/**
 * A DTO for the {@link com.malu.base.gift.domain.Gift} entity.
 */

@Data
public class GiftConsumerVM implements Serializable {

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

    private List<GiftLangConsumerVM> languages = new ArrayList<>();

    @JsonIgnore
    private GiftSeasonConsumerVM giftSeason;

    @JsonIgnore
    private String langCode;

    private Instant createdDate;

    private Instant lastModifiedDate;

    public void setLangCode(String langCode) {
        Optional<GiftLangConsumerVM> defaultLanguageLocal = languages
            .stream()
            .filter(lang -> langCode.equals(lang.getLangCode()))
            .findFirst();
        if (defaultLanguageLocal.isPresent()) {
            name = defaultLanguageLocal.get().getName();
        } else {
            Optional<GiftLangConsumerVM> optLanguageDefault = languages
                .stream()
                .filter(lang -> langCode.equals(ApplicationConstant.LANGUAGE_DEFAULT))
                .findFirst();
            optLanguageDefault.ifPresent(giftLangConsumerVM -> name = giftLangConsumerVM.getName());
        }
        this.langCode = langCode;
    }
}
