package com.malu.base.gift.service.dto;

import com.malu.base.gift.domain.enumeration.EnumGiftStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.malu.base.gift.domain.Gift} entity.
 */
public class GiftDTO implements Serializable {

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
    private BigDecimal originalPrice;

    @NotNull
    private BigDecimal price;

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

    private GiftSeasonDTO giftSeason;

    private GiftProviderDTO giftProvider;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Instant publishDate) {
        this.publishDate = publishDate;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Instant expireDate) {
        this.expireDate = expireDate;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public EnumGiftStatus getStatus() {
        return status;
    }

    public void setStatus(EnumGiftStatus status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GiftSeasonDTO getGiftSeason() {
        return giftSeason;
    }

    public void setGiftSeason(GiftSeasonDTO giftSeason) {
        this.giftSeason = giftSeason;
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
        if (!(o instanceof GiftDTO)) {
            return false;
        }

        GiftDTO giftDTO = (GiftDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, giftDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GiftDTO{" +
            "id=" + getId() +
            ", hashCode='" + getHashCode() + "'" +
            ", code='" + getCode() + "'" +
            ", serial='" + getSerial() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", icon='" + getIcon() + "'" +
            ", mediaPath='" + getMediaPath() + "'" +
            ", originalPrice=" + getOriginalPrice() +
            ", price=" + getPrice() +
            ", publishDate='" + getPublishDate() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", expireDate='" + getExpireDate() + "'" +
            ", useGuide='" + getUseGuide() + "'" +
            ", terms='" + getTerms() + "'" +
            ", tags='" + getTags() + "'" +
            ", status='" + getStatus() + "'" +
            ", userId='" + getUserId() + "'" +
            ", giftSeason=" + getGiftSeason() +
            ", giftProvider=" + getGiftProvider() +
            "}";
    }
}
