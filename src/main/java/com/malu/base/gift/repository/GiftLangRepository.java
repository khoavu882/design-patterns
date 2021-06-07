package com.malu.base.gift.repository;

import com.malu.base.gift.domain.GiftLang;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GiftLang entity.
 */
@SuppressWarnings("unused")
@Repository
@Primary
public interface GiftLangRepository extends JpaRepository<GiftLang, Long> {}
