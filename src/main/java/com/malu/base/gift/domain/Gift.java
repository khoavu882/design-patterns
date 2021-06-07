package com.malu.base.gift.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.malu.base.gift.domain.enumeration.EnumGiftStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Gift.
 */
@Entity
@Table(name = "gift")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gift extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 20)
    @Column(name = "code", length = 20, unique = true)
    private String code;

    @Size(max = 200)
    @Column(name = "name", length = 200)
    private String name;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Size(max = 200)
    @Column(name = "icon", length = 200)
    private String icon;

    @Size(max = 200)
    @Column(name = "media_path", length = 200)
    private String mediaPath;

    @NotNull
    @Column(name = "original_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal originalPrice;

    @NotNull
    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "publish_date")
    private Instant publishDate;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "expire_date")
    private Instant expireDate;

    @Column(name = "use_guide")
    private String useGuide;

    @Column(name = "terms")
    private String terms;

    @Size(max = 500)
    @Column(name = "tags", length = 500)
    private String tags;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumGiftStatus status;

    @Column(name = "user_id")
    private String userId;

    @OneToMany(mappedBy = "gift")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "gift" }, allowSetters = true)
    private List<GiftLang> languages = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "gifts", "giftProvider" }, allowSetters = true)
    private GiftSeason giftSeason;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gift id(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public Gift code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Gift name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Gift description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return this.icon;
    }

    public Gift icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMediaPath() {
        return this.mediaPath;
    }

    public Gift mediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
        return this;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public BigDecimal getOriginalPrice() {
        return this.originalPrice;
    }

    public Gift originalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Gift price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getPublishDate() {
        return this.publishDate;
    }

    public Gift publishDate(Instant publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public void setPublishDate(Instant publishDate) {
        this.publishDate = publishDate;
    }

    public Instant getStartDate() {
        return this.startDate;
    }

    public Gift startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getExpireDate() {
        return this.expireDate;
    }

    public Gift expireDate(Instant expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public void setExpireDate(Instant expireDate) {
        this.expireDate = expireDate;
    }

    public String getUseGuide() {
        return this.useGuide;
    }

    public Gift useGuide(String useGuide) {
        this.useGuide = useGuide;
        return this;
    }

    public void setUseGuide(String useGuide) {
        this.useGuide = useGuide;
    }

    public String getTerms() {
        return this.terms;
    }

    public Gift terms(String terms) {
        this.terms = terms;
        return this;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getTags() {
        return this.tags;
    }

    public Gift tags(String tags) {
        this.tags = tags;
        return this;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public EnumGiftStatus getStatus() {
        return this.status;
    }

    public Gift status(EnumGiftStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(EnumGiftStatus status) {
        this.status = status;
    }

    public String getUserId() {
        return this.userId;
    }

    public Gift userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<GiftLang> getLanguages() {
        return this.languages;
    }

    public Gift languages(List<GiftLang> giftLangs) {
        this.setLanguages(giftLangs);
        return this;
    }

    public Gift addLanguages(GiftLang giftLang) {
        this.languages.add(giftLang);
        giftLang.setGift(this);
        return this;
    }

    public Gift removeLanguages(GiftLang giftLang) {
        this.languages.remove(giftLang);
        giftLang.setGift(null);
        return this;
    }

    public void setLanguages(List<GiftLang> giftLangs) {
        if (this.languages != null) {
            this.languages.forEach(i -> i.setGift(null));
        }
        if (giftLangs != null) {
            giftLangs.forEach(i -> i.setGift(this));
        }
        this.languages = giftLangs;
    }

    public GiftSeason getGiftSeason() {
        return this.giftSeason;
    }

    public Gift giftSeason(GiftSeason giftSeason) {
        this.setGiftSeason(giftSeason);
        return this;
    }

    public void setGiftSeason(GiftSeason giftSeason) {
        this.giftSeason = giftSeason;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gift)) {
            return false;
        }
        return id != null && id.equals(((Gift) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Gift{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
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
            "}";
    }
}
