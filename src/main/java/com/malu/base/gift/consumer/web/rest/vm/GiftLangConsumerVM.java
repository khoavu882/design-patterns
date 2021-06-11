package com.malu.base.gift.consumer.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftLang} entity.
 */

@Data
public class GiftLangConsumerVM implements Serializable {

    @JsonIgnore
    private Long id;

    private String name;

    private String description;

    private String useGuide;

    private String terms;

    private String langCode;

    @JsonIgnore
    private ActionStatus status;
}
