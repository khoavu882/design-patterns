package com.malu.base.gift.service;

import com.malu.base.gift.service.dto.GiftSeasonDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.malu.base.gift.domain.GiftSeason}.
 */
public interface GiftSeasonService {
    /**
     * Save a giftSeason.
     *
     * @param giftSeasonDTO the entity to save.
     * @return the persisted entity.
     */
    GiftSeasonDTO save(GiftSeasonDTO giftSeasonDTO);

    /**
     * Partially updates a giftSeason.
     *
     * @param giftSeasonDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GiftSeasonDTO> partialUpdate(GiftSeasonDTO giftSeasonDTO);

    /**
     * Get all the giftSeasons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftSeasonDTO> findAll(Pageable pageable);

    /**
     * Get the "id" giftSeason.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GiftSeasonDTO> findOne(Long id);

    /**
     * Delete the "id" giftSeason.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
