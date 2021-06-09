package com.malu.base.gift.consumer.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftProvider} entity.
 */

@Data
public class GiftProviderConsumerVM implements Serializable {

    @JsonIgnore
    private Long id;

    private String code;

    private String name;

    private String website;

    private String icon;

    @JsonIgnore
    private ActionStatus status;

    private Instant createdDate;

    private Instant lastModifiedDate;
}
