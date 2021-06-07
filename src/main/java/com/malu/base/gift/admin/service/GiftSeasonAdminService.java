package com.malu.base.gift.admin.service;

import com.malu.base.gift.admin.service.dto.GiftSeasonAdminDTO;
import com.malu.base.gift.admin.web.rest.vm.GiftSeasonAdminVM;
import com.malu.base.gift.service.GiftSeasonExtService;
import com.malu.base.gift.service.dto.GiftSeasonExtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:19
 */
public interface GiftSeasonAdminService extends GiftSeasonExtService {

    /**
     * create a giftSeason.
     *
     * @param giftSeasonAdminDTO the entity to create.
     * @return the persisted entity.
     */
    GiftSeasonAdminVM createByAdmin(GiftSeasonAdminDTO giftSeasonAdminDTO);

    /**
     * update a giftSeason.
     *
     * @param giftSeasonAdminDTO the entity to update.
     * @return the persisted entity.
     */
    GiftSeasonAdminVM updateByAdmin(GiftSeasonAdminDTO giftSeasonAdminDTO);

    /**
     * Get all the giftSeason.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftSeasonAdminVM> findAllWithFilterByAdmin(String keyword, Pageable pageable);

    /**
     * Get the "id" giftSeason.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    GiftSeasonAdminVM findOneByAdmin(Long id);
}
