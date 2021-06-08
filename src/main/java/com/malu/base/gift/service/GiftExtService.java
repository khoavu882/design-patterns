package com.malu.base.gift.service;

import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.dto.GiftDTO;
import com.malu.base.gift.service.dto.GiftExtDTO;

import java.util.Optional;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:19
 */
public interface GiftExtService extends GiftService {

    /**
     * Save a gift.
     *
     * @param gift the entity to save.
     * @return the persisted entity.
     */
    Gift save(Gift gift);

    /**
     * create a gift.
     *
     * @param giftExtDTO the entity to create.
     * @return the persisted entity.
     */
    Gift create(GiftExtDTO giftExtDTO);

    /**
     * update a gift.
     *
     * @param giftExtDTO the entity to update.
     * @return the persisted entity.
     */
    Gift update(GiftExtDTO giftExtDTO);

    /**
     * Get the "id" gift.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Gift findOneById(Long id);
}
