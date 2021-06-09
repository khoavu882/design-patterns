package com.malu.base.gift.repository;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftLang;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:14
 */

@Repository
@Qualifier(ApplicationConstant.EXTEND)
public interface GiftLangExtRepository extends GiftLangRepository {

    @Query(value = "FROM GiftLang gl WHERE gl.langCode = :langCode AND gl.gift.id = :giftId ")
    Optional<GiftLang> findByLangCodeAndGiftId(@Param("langCode") String langCode, @Param("giftId") Long giftId);
}
