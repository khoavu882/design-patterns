package com.malu.base.gift.admin.repository;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.repository.GiftExtRepository;
import com.malu.base.gift.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:13
 */

@Repository
@Qualifier(ApplicationConstant.ADMIN)
public interface GiftAdminRepository extends GiftExtRepository {
}
