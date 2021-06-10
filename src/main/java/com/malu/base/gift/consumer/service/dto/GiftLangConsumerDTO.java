package com.malu.base.gift.consumer.service.dto;

import com.malu.base.gift.domain.enumeration.ActionStatus;
import java.io.Serializable;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftLang} entity.
 */

@Data
public class GiftLangConsumerDTO implements Serializable {

    private Long id;

    @Size(max = 200)
    private String name;

    @Size(max = 500)
    private String description;

    private String useGuide;

    private String terms;

    @Size(max = 10)
    private String langCode;

    private ActionStatus status;

    private GiftConsumerDTO gift;
}
