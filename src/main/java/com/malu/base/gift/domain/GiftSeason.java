package com.malu.base.gift.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A GiftSeason.
 */
@Entity
@Table(name = "gift_season")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GiftSeason extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Size(max = 200)
    @Column(name = "icon", length = 200)
    private String icon;

    @OneToMany(mappedBy = "giftSeason")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "languages", "giftSeason" }, allowSetters = true)
    private List<Gift> gifts = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "giftSeasons" }, allowSetters = true)
    private GiftProvider giftProvider;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GiftSeason id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public GiftSeason name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public GiftSeason description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return this.icon;
    }

    public GiftSeason icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Gift> getGifts() {
        return this.gifts;
    }

    public GiftSeason gifts(List<Gift> gifts) {
        this.setGifts(gifts);
        return this;
    }

    public GiftSeason addGift(Gift gift) {
        this.gifts.add(gift);
        gift.setGiftSeason(this);
        return this;
    }

    public GiftSeason removeGift(Gift gift) {
        this.gifts.remove(gift);
        gift.setGiftSeason(null);
        return this;
    }

    public void setGifts(List<Gift> gifts) {
        if (this.gifts != null) {
            this.gifts.forEach(i -> i.setGiftSeason(null));
        }
        if (gifts != null) {
            gifts.forEach(i -> i.setGiftSeason(this));
        }
        this.gifts = gifts;
    }

    public GiftProvider getGiftProvider() {
        return this.giftProvider;
    }

    public GiftSeason giftProvider(GiftProvider giftProvider) {
        this.setGiftProvider(giftProvider);
        return this;
    }

    public void setGiftProvider(GiftProvider giftProvider) {
        this.giftProvider = giftProvider;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GiftSeason)) {
            return false;
        }
        return id != null && id.equals(((GiftSeason) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GiftSeason{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", icon='" + getIcon() + "'" +
            "}";
    }
}
