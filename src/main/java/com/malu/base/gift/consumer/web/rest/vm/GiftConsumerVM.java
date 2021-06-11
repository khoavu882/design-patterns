package com.malu.base.gift.consumer.web.rest.vm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malu.base.gift.constant.ApplicationConstant;
import lombok.Data;

/**
 * A DTO for the {@link com.malu.base.gift.domain.Gift} entity.
 */

@Data
public class GiftConsumerVM implements Serializable {

    @JsonIgnore
    private Long id;

    private String hashCode;

    private String name;

    private String description;

    private String icon;

    private String mediaPath;

    private BigDecimal originalPrice;

    private BigDecimal price;

    @JsonIgnore
    private Instant publishDate;

    @JsonIgnore
    private Instant startDate;

    @JsonIgnore

    private Instant expireDate;

    private GiftProviderConsumerVM giftProvider;

    private List<GiftLangConsumerVM> languages = new ArrayList<>();

    @JsonIgnore
    private String langCode;

    private Instant createdDate;

    private Instant lastModifiedDate;

    public void setLangCode(String langCode) {
        Optional<GiftLangConsumerVM> defaultLanguageLocal = languages.stream()
            .filter(lang -> langCode.equals(lang.getLangCode())).findFirst();
        if (defaultLanguageLocal.isPresent()) {
            name = defaultLanguageLocal.get().getName();
        } else {
            Optional<GiftLangConsumerVM> optLanguageDefault = languages.stream()
                .filter(lang -> langCode.equals(ApplicationConstant.LANGUAGE_DEFAULT)).findFirst();
            optLanguageDefault.ifPresent(giftLangAdminVM -> name = giftLangAdminVM.getName());
        }
        this.langCode = langCode;
    }
}
