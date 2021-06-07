package com.malu.base.gift.service;

import com.malu.base.gift.service.dto.GiftDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.malu.base.gift.domain.Gift}.
 */
public interface GiftService {
    /**
     * Save a gift.
     *
     * @param giftDTO the entity to save.
     * @return the persisted entity.
     */
    GiftDTO save(GiftDTO giftDTO);

    /**
     * Partially updates a gift.
     *
     * @param giftDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GiftDTO> partialUpdate(GiftDTO giftDTO);

    /**
     * Get all the gifts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftDTO> findAll(Pageable pageable);

    /**
     * Get the "id" gift.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GiftDTO> findOne(Long id);

    /**
     * Delete the "id" gift.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
