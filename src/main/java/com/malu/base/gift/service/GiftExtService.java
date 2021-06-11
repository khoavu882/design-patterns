package com.malu.base.gift.service;

import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.domain.enumeration.EnumGiftStatus;
import com.malu.base.gift.service.dto.GiftExtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * Get all the gift.
     *
     * @param keyword .
     * @param status .
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Gift> findAllWithFilter(String keyword, EnumGiftStatus status, Pageable pageable);

    /**
     * Get the "id" gift.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Gift findOneById(Long id);

    /**
     * Get the "code" gift.
     *
     * @param code the id of the entity.
     * @return the entity.
     */
    void checkExistsByCode(String code);

    /**
     * Get the "hashCode" gift.
     *
     * @param hashCode the hashCode of the entity.
     * @return the entity.
     */
    Gift findOneByHashCode(String hashCode);
}
