package com.malu.base.gift.consumer.repository;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.repository.GiftProviderExtRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 04/06/2021
 * Time: 21:24
 */

@Repository
@Qualifier(ApplicationConstant.CONSUMER)
public interface GiftProviderConsumerRepository extends GiftProviderExtRepository {}
