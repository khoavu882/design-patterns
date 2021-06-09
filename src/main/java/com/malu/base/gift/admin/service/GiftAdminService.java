package com.malu.base.gift.admin.service;

import com.malu.base.gift.admin.service.dto.GiftAdminDTO;
import com.malu.base.gift.admin.web.rest.vm.GiftAdminVM;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.service.GiftExtService;
import com.malu.base.gift.service.dto.GiftExtDTO;
import com.malu.base.gift.service.dto.GiftSeasonExtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:19
 */
public interface GiftAdminService extends GiftExtService {

    /**
     * create a gift.
     *
     * @param giftAdminDTO the entity to create.
     * @return the persisted entity.
     */
    GiftAdminVM createByAdmin(GiftAdminDTO giftAdminDTO);

    /**
     * update a gift.
     *
     * @param giftAdminDTO the entity to update.
     * @return the persisted entity.
     */
    GiftAdminVM updateByAdmin(GiftAdminDTO giftAdminDTO);

    /**
     * Get all the gift.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftAdminVM> findAllWithFilterByAdmin(String keyword, ActionStatus status, Pageable pageable);

    /**
     * Get the "id" gift.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    GiftAdminVM adminFindOneById(Long id);

    /**
     * Get the "hashCode" gift.
     *
     * @param hashCode the hashCode of the entity.
     * @return the entity.
     */
    GiftAdminVM adminFindOneByHashCode(String hashCode);
}
