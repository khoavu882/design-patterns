package com.malu.base.gift.consumer.service;

import com.malu.base.gift.consumer.service.dto.GiftSeasonConsumerDTO;
import com.malu.base.gift.consumer.web.rest.vm.GiftSeasonConsumerVM;
import com.malu.base.gift.service.GiftSeasonExtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:19
 */
public interface GiftSeasonConsumerService extends GiftSeasonExtService {
    /**
     * create a giftSeason.
     *
     * @param giftSeasonConsumerDTO the entity to create.
     * @return the persisted entity.
     */
    GiftSeasonConsumerVM createByConsumer(GiftSeasonConsumerDTO giftSeasonConsumerDTO);

    /**
     * update a giftSeason.
     *
     * @param giftSeasonConsumerDTO the entity to update.
     * @return the persisted entity.
     */
    GiftSeasonConsumerVM updateByConsumer(GiftSeasonConsumerDTO giftSeasonConsumerDTO);

    /**
     * Get all the giftSeason.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftSeasonConsumerVM> findAllWithFilterByConsumer(String keyword, Pageable pageable);

    /**
     * Get the "id" giftSeason.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    GiftSeasonConsumerVM findOneByConsumer(Long id);
}
