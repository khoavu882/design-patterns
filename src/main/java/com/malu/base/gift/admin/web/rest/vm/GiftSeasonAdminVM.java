package com.malu.base.gift.admin.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malu.base.gift.service.dto.GiftProviderDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftSeason} entity.
 */

@Data
public class GiftSeasonAdminVM implements Serializable {

    @JsonIgnore
    private Long id;

    private String name;

    private String description;

    private String icon;

    private GiftProviderAdminVM giftProvider;

    private Instant createdDate;

    private Instant lastModifiedDate;
}
