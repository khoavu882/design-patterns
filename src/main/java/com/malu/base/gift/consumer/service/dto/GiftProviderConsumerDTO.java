package com.malu.base.gift.consumer.service.dto;

import com.malu.base.gift.domain.enumeration.ActionStatus;
import java.io.Serializable;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftProvider} entity.
 */

@Data
public class GiftProviderConsumerDTO implements Serializable {

    private Long id;

    @Size(max = 200)
    private String name;

    @Size(max = 50)
    private String website;

    private ActionStatus status;
}
