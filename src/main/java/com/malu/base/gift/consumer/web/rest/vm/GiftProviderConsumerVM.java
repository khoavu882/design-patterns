package com.malu.base.gift.consumer.web.rest.vm;

import com.malu.base.gift.domain.enumeration.ActionStatus;
import java.io.Serializable;
import java.time.Instant;
import lombok.Data;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftProvider} entity.
 */

@Data
public class GiftProviderConsumerVM implements Serializable {

    private Long id;

    private String code;

    private String name;

    private String website;

    private String icon;

    private ActionStatus status;

    private Instant createdDate;

    private Instant lastModifiedDate;
}
