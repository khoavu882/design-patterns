package com.malu.base.gift.admin.service.dto;

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
public class GiftLangAdminDTO implements Serializable {

    private Long id;

    @Size(max = 200)
    private String name;

    @Size(max = 500)
    private String description;

    private String useGuide;

    private String terms;

    @Size(max = 10)
    private String langCode;

    private ActionStatus status;

    private GiftAdminDTO gift;
}
