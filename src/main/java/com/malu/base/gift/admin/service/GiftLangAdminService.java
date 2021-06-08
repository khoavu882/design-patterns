package com.malu.base.gift.admin.service;

import com.malu.base.gift.admin.service.dto.GiftLangAdminDTO;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.service.GiftLangService;
import com.malu.base.gift.service.dto.GiftLangExtDTO;

import java.util.List;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:18
 */
public interface GiftLangAdminService extends GiftLangService {

    /**
     * Save a giftLang.
     *
     * @param giftLang the entity to save.
     * @return the persisted entity.
     */
    GiftLang save(GiftLang giftLang);

    /**
     * Save list giftLangAdmin .
     *
     * @param giftLangAdminDTOList the entity to save.
     * @param gift parent of the entity to save.
     * @return the persisted entity.
     */
    void translateGift(List<GiftLangAdminDTO> giftLangAdminDTOList, Gift gift, boolean isCreate);

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
}
