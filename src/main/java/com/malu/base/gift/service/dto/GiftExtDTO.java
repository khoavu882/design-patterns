package com.malu.base.gift.service.dto;

import com.malu.base.gift.domain.enumeration.EnumGiftStatus;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.malu.base.gift.domain.Gift} entity.
 */

@Data
public class GiftExtDTO implements Serializable {

    private Long id;

    @Size(max = 200)
    private String hashCode;

    @Size(max = 200)
    private String code;

    @Size(max = 200)
    private String serial;

    @Size(max = 500)
    private String name;

    @Size(max = 2000)
    private String description;

    @Size(max = 200)
    private String icon;

    @Size(max = 200)
    private String mediaPath;

    @NotNull
    private BigDecimal price;
    
    @NotNull
    private BigDecimal originalPrice;

    private Instant publishDate;

    private Instant startDate;

    private Instant expireDate;

    @Size(max = 5000)
    private String useGuide;

    @Size(max = 5000)
    private String terms;

    @Size(max = 5000)
    private String tags;

    private EnumGiftStatus status;

    @Size(max = 20)
    private String userId;

    private GiftSeasonExtDTO giftSeason;

    private GiftProviderExtDTO giftProvider;

    @Valid
    @NotEmpty
    private List<GiftLangExtDTO> languages = new ArrayList<>();
}
