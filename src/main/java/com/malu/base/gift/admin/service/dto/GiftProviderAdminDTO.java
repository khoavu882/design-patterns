package com.malu.base.gift.admin.service.dto;

import com.malu.base.gift.domain.enumeration.ActionStatus;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftProvider} entity.
 */

@Data
public class GiftProviderAdminDTO implements Serializable {

    private Long id;

    @Size(max = 200)
    private String name;

    @Size(max = 50)
    private String website;

    private ActionStatus status;
}
