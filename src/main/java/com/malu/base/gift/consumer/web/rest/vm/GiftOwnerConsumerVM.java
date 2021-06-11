package com.malu.base.gift.consumer.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malu.base.gift.admin.web.rest.vm.GiftLangAdminVM;
import com.malu.base.gift.admin.web.rest.vm.GiftProviderAdminVM;
import com.malu.base.gift.constant.ApplicationConstant;
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
public class GiftOwnerConsumerVM implements Serializable {

    @JsonIgnore
    private Long id;

    private String hashCode;

    private String code;

    private String name;

    private String description;

    private String icon;

    private String mediaPath;

    private BigDecimal originalPrice;

    private BigDecimal price;

    @JsonIgnore
    private Instant publishDate;

    private Instant startDate;

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
