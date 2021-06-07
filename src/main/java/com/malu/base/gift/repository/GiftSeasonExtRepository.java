package com.malu.base.gift.repository;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftSeason;
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
 * Time: 23:02
 */
@Repository
@Qualifier(ApplicationConstant.EXTEND)
public interface GiftSeasonExtRepository extends GiftSeasonRepository {

    @Query(value = "FROM GiftSeason gs WHERE (:keyword Is NULL OR gs.name LIKE %:keyword%)")
    Page<GiftSeason> findAllWithFilter(@Param("keyword") String keyword,
                                       Pageable pageable);
}
