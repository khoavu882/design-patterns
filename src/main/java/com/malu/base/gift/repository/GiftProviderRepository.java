package com.malu.base.gift.repository;

import com.malu.base.gift.domain.GiftProvider;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GiftProvider entity.
 */
@SuppressWarnings("unused")
@Repository
@Primary
public interface GiftProviderRepository extends JpaRepository<GiftProvider, Long> {}
