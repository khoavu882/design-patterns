package com.malu.base.gift.consumer.web.rest.vm;

import com.malu.base.gift.domain.enumeration.ActionStatus;
import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftLang} entity.
 */

@Data
public class GiftLangConsumerVM implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String useGuide;

    private String terms;

    private String langCode;

    private ActionStatus status;
}
