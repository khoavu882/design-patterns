package com.malu.base.gift.admin.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftProvider} entity.
 */

@Data
public class GiftProviderAdminVM implements Serializable {

    private Long id;

    private String code;

    private String name;

    private String website;

    private String icon;

    private ActionStatus status;

    private Instant createdDate;

    private Instant lastModifiedDate;
}
