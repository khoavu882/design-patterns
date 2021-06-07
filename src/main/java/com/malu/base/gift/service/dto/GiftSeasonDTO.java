package com.malu.base.gift.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftSeason} entity.
 */
public class GiftSeasonDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 200)
    private String name;

    @Size(max = 500)
    private String description;

    @Size(max = 200)
    private String icon;

    private GiftProviderDTO giftProvider;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public GiftProviderDTO getGiftProvider() {
        return giftProvider;
    }

    public void setGiftProvider(GiftProviderDTO giftProvider) {
        this.giftProvider = giftProvider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GiftSeasonDTO)) {
            return false;
        }

        GiftSeasonDTO giftSeasonDTO = (GiftSeasonDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, giftSeasonDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GiftSeasonDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", icon='" + getIcon() + "'" +
            ", giftProvider=" + getGiftProvider() +
            "}";
    }
}
