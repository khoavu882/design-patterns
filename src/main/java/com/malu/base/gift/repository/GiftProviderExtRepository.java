package com.malu.base.gift.repository;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:16
 */

@Repository
@Qualifier(ApplicationConstant.EXTEND)
public interface GiftProviderExtRepository extends GiftProviderRepository {

    @Query(value = "FROM GiftProvider gp WHERE gp.status <> 'DELETED' " +
        "AND (:status IS NULL OR gp.status = :status) " +
        "AND (:keyword IS NULL OR gp.code LIKE %:keyword% OR gp.name LIKE %:keyword% OR gp.website LIKE %:keyword%)")
    Page<GiftProvider> findAllWithFilter(@Param("keyword") String keyword,
                                         @Param("status") ActionStatus status,
                                         Pageable pageable);
}
