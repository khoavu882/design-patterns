package com.malu.base.gift.admin.repository;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.repository.GiftProviderExtRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 04/06/2021
 * Time: 21:24
 */

@Repository
@Qualifier(ApplicationConstant.ADMIN)
public interface GiftProviderAdminRepository extends GiftProviderExtRepository {
}
