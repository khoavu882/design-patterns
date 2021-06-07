package com.malu.base.gift.repository;

import com.malu.base.gift.domain.Gift;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Gift entity.
 */
@SuppressWarnings("unused")
@Repository
@Primary
public interface GiftRepository extends JpaRepository<Gift, Long> {}
