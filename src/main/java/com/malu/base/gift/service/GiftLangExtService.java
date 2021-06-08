package com.malu.base.gift.service;

import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.service.dto.GiftLangExtDTO;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:18
 */
public interface GiftLangExtService extends GiftLangService {

    /**
     * Save a giftLang.
     *
     * @param giftLang the entity to save.
     * @return the persisted entity.
     */
    GiftLang save(GiftLang giftLang);

    /**
     * Create a giftLang.
     *
     * @param giftLangExtDTO the entity to save.
     * @return the persisted entity.
     */
    GiftLang create(GiftLangExtDTO giftLangExtDTO);

    /**
     * Update a giftLang.
     *
     * @param giftLangExtDTO the entity to save.
     * @return the persisted entity.
     */
    GiftLang update(GiftLangExtDTO giftLangExtDTO);

    /**
     * Get GiftLang by Language Code and Gift ID
     *
     * @param languageCode .
     * @param gitId .
     * @return the persisted entity.
     */
    GiftLang findOneByLanguageCodeAndGiftId(String languageCode, Long gitId);
}
