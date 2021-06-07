package com.malu.base.gift.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A GiftLang.
 */
@Entity
@Table(name = "gift_lang")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GiftLang implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 200)
    @Column(name = "name", length = 200)
    private String name;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "use_guide")
    private String useGuide;

    @Column(name = "terms")
    private String terms;

    @Size(max = 10)
    @Column(name = "lang_code", length = 10)
    private String langCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ActionStatus status;

    @ManyToOne
    @JsonIgnoreProperties(value = { "languages", "giftSeason" }, allowSetters = true)
    private Gift gift;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GiftLang id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public GiftLang name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public GiftLang description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUseGuide() {
        return this.useGuide;
    }

    public GiftLang useGuide(String useGuide) {
        this.useGuide = useGuide;
        return this;
    }

    public void setUseGuide(String useGuide) {
        this.useGuide = useGuide;
    }

    public String getTerms() {
        return this.terms;
    }

    public GiftLang terms(String terms) {
        this.terms = terms;
        return this;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getLangCode() {
        return this.langCode;
    }

    public GiftLang langCode(String langCode) {
        this.langCode = langCode;
        return this;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public ActionStatus getStatus() {
        return this.status;
    }

    public GiftLang status(ActionStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }

    public Gift getGift() {
        return this.gift;
    }

    public GiftLang gift(Gift gift) {
        this.setGift(gift);
        return this;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GiftLang)) {
            return false;
        }
        return id != null && id.equals(((GiftLang) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GiftLang{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", useGuide='" + getUseGuide() + "'" +
            ", terms='" + getTerms() + "'" +
            ", langCode='" + getLangCode() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
