package com.malu.base.gift.consumer.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.Instant;
import lombok.Data;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftSeason} entity.
 */

@Data
public class GiftSeasonConsumerVM implements Serializable {

    @JsonIgnore
    private Long id;

    private String name;

    private String description;

    private String icon;

    private GiftProviderConsumerVM giftProvider;

    private Instant createdDate;

    private Instant lastModifiedDate;
}
