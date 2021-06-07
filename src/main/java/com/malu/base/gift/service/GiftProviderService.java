package com.malu.base.gift.service;

import com.malu.base.gift.service.dto.GiftProviderDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.malu.base.gift.domain.GiftProvider}.
 */
public interface GiftProviderService {
    /**
     * Save a giftProvider.
     *
     * @param giftProviderDTO the entity to save.
     * @return the persisted entity.
     */
    GiftProviderDTO save(GiftProviderDTO giftProviderDTO);

    /**
     * Partially updates a giftProvider.
     *
     * @param giftProviderDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GiftProviderDTO> partialUpdate(GiftProviderDTO giftProviderDTO);

    /**
     * Get all the giftProviders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftProviderDTO> findAll(Pageable pageable);

    /**
     * Get the "id" giftProvider.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GiftProviderDTO> findOne(Long id);

    /**
     * Delete the "id" giftProvider.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
