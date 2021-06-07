package com.malu.base.gift.service.dto;

import com.malu.base.gift.domain.enumeration.ActionStatus;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.malu.base.gift.domain.GiftLang} entity.
 */
public class GiftLangExtDTO implements Serializable {

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

    private Long giftId;

    private GiftExtDTO gift;

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

    public String getUseGuide() {
        return useGuide;
    }

    public void setUseGuide(String useGuide) {
        this.useGuide = useGuide;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public ActionStatus getStatus() {
        return status;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public GiftExtDTO getGift() {
        return gift;
    }

    public void setGift(GiftExtDTO gift) {
        this.gift = gift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GiftLangExtDTO)) {
            return false;
        }

        GiftLangExtDTO giftLangDTO = (GiftLangExtDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, giftLangDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GiftLangDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", useGuide='" + getUseGuide() + "'" +
            ", terms='" + getTerms() + "'" +
            ", langCode='" + getLangCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", gift=" + getGift() +
            "}";
    }
}
