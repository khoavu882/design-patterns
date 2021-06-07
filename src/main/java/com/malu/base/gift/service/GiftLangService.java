package com.malu.base.gift.service;

import com.malu.base.gift.service.dto.GiftLangDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.malu.base.gift.domain.GiftLang}.
 */
public interface GiftLangService {
    /**
     * Save a giftLang.
     *
     * @param giftLangDTO the entity to save.
     * @return the persisted entity.
     */
    GiftLangDTO save(GiftLangDTO giftLangDTO);

    /**
     * Partially updates a giftLang.
     *
     * @param giftLangDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GiftLangDTO> partialUpdate(GiftLangDTO giftLangDTO);

    /**
     * Get all the giftLangs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftLangDTO> findAll(Pageable pageable);

    /**
     * Get the "id" giftLang.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GiftLangDTO> findOne(Long id);

    /**
     * Delete the "id" giftLang.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
