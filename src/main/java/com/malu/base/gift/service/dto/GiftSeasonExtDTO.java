package com.malu.base.gift.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftSeason} entity.
 */

@Data
public class GiftSeasonExtDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 200)
    private String name;

    @Size(max = 500)
    private String description;

    @Size(max = 200)
    private String icon;

    private Long giftProviderId;

    private GiftProviderExtDTO giftProvider;
}
