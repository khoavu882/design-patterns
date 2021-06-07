package com.malu.base.gift.service;

import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.service.dto.GiftSeasonExtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:19
 */
public interface GiftSeasonExtService extends GiftSeasonService{

    /**
     * Save a giftSeason.
     *
     * @param giftSeason the entity to save.
     * @return the persisted entity.
     */
    GiftSeason save(GiftSeason giftSeason);

    /**
     * create a giftSeason.
     *
     * @param giftSeasonExtDTO the entity to create.
     * @return the persisted entity.
     */
    GiftSeason create(GiftSeasonExtDTO giftSeasonExtDTO);

    /**
     * update a giftSeason.
     *
     * @param giftSeasonExtDTO the entity to update.
     * @return the persisted entity.
     */
    GiftSeason update(GiftSeasonExtDTO giftSeasonExtDTO);

    /**
     * Get all the giftProviders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftSeason> findAllWithFilter(String keyword, Pageable pageable);

    /**
     * Get the "id" giftSeason.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    GiftSeason findOneById(Long id);
}
