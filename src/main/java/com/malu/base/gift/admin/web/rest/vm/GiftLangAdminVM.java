package com.malu.base.gift.admin.web.rest.vm;

import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.service.dto.GiftExtDTO;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftLang} entity.
 */

@Data
public class GiftLangAdminVM implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String useGuide;

    private String terms;

    private String langCode;

    private ActionStatus status;
}
