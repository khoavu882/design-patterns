package com.malu.base.gift.service.dto;

import com.malu.base.gift.domain.enumeration.ActionStatus;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftProvider} entity.
 */
public class GiftProviderDTO implements Serializable {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ActionStatus getStatus() {
        return status;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GiftProviderDTO)) {
            return false;
        }

        GiftProviderDTO giftProviderDTO = (GiftProviderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, giftProviderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GiftProviderDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", website='" + getWebsite() + "'" +
            ", icon='" + getIcon() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
