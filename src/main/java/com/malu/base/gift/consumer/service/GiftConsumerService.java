package com.malu.base.gift.consumer.service;

import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.consumer.web.rest.vm.GiftOwnerConsumerVM;
import com.malu.base.gift.service.GiftExtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:19
 */
public interface GiftConsumerService extends GiftExtService {

    /**
     * Get all the gift.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftConsumerVM> findAllWithFilterByConsumer(String keyword, Pageable pageable);

    /**
     * Get all the gift.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftOwnerConsumerVM> findAllWithFilterByOwner(String keyword, Pageable pageable);
}
