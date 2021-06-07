package com.malu.base.gift.service.dto;

import com.malu.base.gift.domain.enumeration.ActionStatus;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftProvider} entity.
 */

@Data
public class GiftProviderExtDTO implements Serializable {

    private Long id;

    @Size(max = 20)
    private String code;

    @Size(max = 200)
    private String name;

    @Size(max = 50)
    private String website;

    @Size(max = 200)
    private String icon;

    private ActionStatus status;

    private Instant createdDate;

    private Instant lastModifiedDate;
}
