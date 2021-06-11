package com.malu.base.gift.consumer.web.rest.vm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;

/**
 * A DTO for the {@link com.malu.base.gift.domain.Gift} entity.
 */

@Data
public class GiftConsumerVM implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String icon;

    private String mediaPath;

    private BigDecimal originalPrice;

    private BigDecimal price;

    private Instant publishDate;

    private Instant startDate;

    private Instant expireDate;
}
