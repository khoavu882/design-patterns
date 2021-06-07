package com.malu.base.gift.repository;

import com.malu.base.gift.constant.ApplicationConstant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:14
 */

@Repository
@Qualifier(ApplicationConstant.EXTEND)
public interface GiftLangExtRepository extends GiftLangRepository {
}
