package com.malu.base.gift.consumer.repository;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.repository.GiftExtRepository;
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
 * Time: 23:13
 */

@Repository
@Qualifier(ApplicationConstant.CONSUMER)
public interface GiftConsumerRepository extends GiftExtRepository {
    @Query(value = "FROM Gift g WHERE (:keyword Is NULL OR g.name LIKE %:keyword%)")
    Page<Gift> findAllWithFilter(@Param("keyword") String keyword, Pageable pageable);
}
