package com.malu.base.gift.consumer.service;

import com.malu.base.gift.consumer.web.rest.vm.GiftProviderConsumerVM;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.service.GiftProviderExtService;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 04/06/2021
 * Time: 21:26
 */
public interface GiftProviderConsumerService extends GiftProviderExtService {
    /**
     * Save a giftProvider.
     *
     * @param giftProviderExtDTO the entity to save.
     * @return the persisted entity.
     */
    GiftProviderConsumerVM createByConsumer(GiftProviderExtDTO giftProviderExtDTO);

    /**
     * Save a giftProvider.
     *
     * @param giftProviderExtDTO the entity to save.
     * @return the persisted entity.
     */
    GiftProviderConsumerVM updateByConsumer(GiftProviderExtDTO giftProviderExtDTO);

    /**
     * Get all the giftProviders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftProviderConsumerVM> findAllWithFilterByConsumer(String keyword, ActionStatus status, Pageable pageable);

    /**
     * Get the "id" giftProvider.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    GiftProviderConsumerVM findOneByConsumer(Long id);

    /**
     * Activate the "id" giftProvider.
     *
     * @param id the id of the entity.
     */
    void activateByConsumer(Long id);

    /**
     * Deactivate the "id" giftProvider.
     *
     * @param id the id of the entity.
     */
    void deactivateByConsumer(Long id);

    /**
     * Delete the "id" giftProvider.
     *
     * @param id the id of the entity.
     */
    void deleteByConsumer(Long id);
}
