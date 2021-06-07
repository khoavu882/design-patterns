package com.malu.base.gift.repository;

import com.malu.base.gift.domain.GiftSeason;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GiftSeason entity.
 */
@SuppressWarnings("unused")
@Repository
@Primary
public interface GiftSeasonRepository extends JpaRepository<GiftSeason, Long> {}
