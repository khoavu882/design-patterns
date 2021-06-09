package com.malu.base.gift.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A GiftProvider.
 */
@Entity
@Table(name = "gift_provider")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GiftProvider extends AbstractAuditingEntity implements Serializable {

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

    @Size(max = 50)
    @Column(name = "website", length = 50)
    private String website;

    @Size(max = 200)
    @Column(name = "icon", length = 200)
    private String icon;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ActionStatus status;

    @OneToMany(mappedBy = "giftProvider")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "gifts", "giftProvider" }, allowSetters = true)
    private List<GiftSeason> giftSeasons = new ArrayList<>();

    @OneToMany(mappedBy = "giftProvider")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "languages", "giftSeason", "giftProvider" }, allowSetters = true)
    private List<Gift> gifts = new ArrayList<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GiftProvider id(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public GiftProvider code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public GiftProvider name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return this.website;
    }

    public GiftProvider website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIcon() {
        return this.icon;
    }

    public GiftProvider icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ActionStatus getStatus() {
        return this.status;
    }

    public GiftProvider status(ActionStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }

    public List<GiftSeason> getGiftSeasons() {
        return this.giftSeasons;
    }

    public GiftProvider giftSeasons(List<GiftSeason> giftSeasons) {
        this.setGiftSeasons(giftSeasons);
        return this;
    }

    public GiftProvider addGiftSeason(GiftSeason giftSeason) {
        this.giftSeasons.add(giftSeason);
        giftSeason.setGiftProvider(this);
        return this;
    }

    public GiftProvider removeGiftSeason(GiftSeason giftSeason) {
        this.giftSeasons.remove(giftSeason);
        giftSeason.setGiftProvider(null);
        return this;
    }

    public void setGiftSeasons(List<GiftSeason> giftSeasons) {
        if (this.giftSeasons != null) {
            this.giftSeasons.forEach(i -> i.setGiftProvider(null));
        }
        if (giftSeasons != null) {
            giftSeasons.forEach(i -> i.setGiftProvider(this));
        }
        this.giftSeasons = giftSeasons;
    }

    public List<Gift> getGifts() {
        return this.gifts;
    }

    public GiftProvider gifts(List<Gift> gifts) {
        this.setGifts(gifts);
        return this;
    }

    public GiftProvider addGift(Gift gift) {
        this.gifts.add(gift);
        gift.setGiftProvider(this);
        return this;
    }

    public GiftProvider removeGift(Gift gift) {
        this.gifts.remove(gift);
        gift.setGiftProvider(null);
        return this;
    }

    public void setGifts(List<Gift> gifts) {
        if (this.gifts != null) {
            this.gifts.forEach(i -> i.setGiftProvider(null));
        }
        if (gifts != null) {
            gifts.forEach(i -> i.setGiftProvider(this));
        }
        this.gifts = gifts;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GiftProvider)) {
            return false;
        }
        return id != null && id.equals(((GiftProvider) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GiftProvider{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", website='" + getWebsite() + "'" +
            ", icon='" + getIcon() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
