package com.malu.base.gift.repository;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:13
 */

@Repository
@Qualifier(ApplicationConstant.EXTEND)
public interface GiftExtRepository extends GiftRepository {

    Optional<Gift> findByHashCode(@Param("hashCode") String hashCode);

    Optional<Gift> findByCode(@Param("code") String code);

    @Query(value = "FROM Gift g WHERE g.userId IS NULL " +
        "AND (:keyword IS NULL OR g.code LIKE %:keyword% OR g.name LIKE %:keyword% OR g.description LIKE %:keyword%) " +
        "AND (:status IS NULL OR g.status = :status)")
    Page<Gift> findAllWithFilter(@Param("keyword") String keyword,
                                 @Param("status") ActionStatus status,
                                 Pageable pageable);
}
