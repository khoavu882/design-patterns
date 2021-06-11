package com.malu.base.gift.consumer.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * A DTO for the {@link com.malu.base.gift.domain.Gift} entity.
 */

@Data
public class GiftConsumerDTO implements Serializable {

    private Long id;

    @Size(max = 500)
    private String name;

    @Size(max = 2000)
    private String description;

    @Size(max = 200)
    private String icon;

    @Size(max = 200)
    private String mediaPath;

    @NotNull
    private BigDecimal originalPrice;

    @NotNull
    private BigDecimal price;

    private Instant publishDate;

    private Instant startDate;

    private Instant expireDate;
}
