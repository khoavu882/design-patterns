package com.malu.base.gift.admin.service;

import com.malu.base.gift.admin.web.rest.vm.GiftProviderAdminVM;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.service.GiftProviderExtService;
import com.malu.base.gift.service.dto.GiftProviderDTO;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 04/06/2021
 * Time: 21:26
 */
public interface GiftProviderAdminService extends GiftProviderExtService {

    /**
     * Save a giftProvider.
     *
     * @param giftProviderExtDTO the entity to save.
     * @return the persisted entity.
     */
    GiftProviderAdminVM createByAdmin(GiftProviderExtDTO giftProviderExtDTO);

    /**
     * Save a giftProvider.
     *
     * @param giftProviderExtDTO the entity to save.
     * @return the persisted entity.
     */
    GiftProviderAdminVM updateByAdmin(GiftProviderExtDTO giftProviderExtDTO);

    /**
     * Get all the giftProviders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftProviderAdminVM> findAllWithFilterByAdmin(String keyword, ActionStatus status, Pageable pageable);

    /**
     * Get the "id" giftProvider.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    GiftProviderAdminVM findOneByAdmin(Long id);

    /**
     * Activate the "id" giftProvider.
     *
     * @param id the id of the entity.
     */
    void activateByAdmin(Long id);

    /**
     * Deactivate the "id" giftProvider.
     *
     * @param id the id of the entity.
     */
    void deactivateByAdmin(Long id);

    /**
     * Delete the "id" giftProvider.
     *
     * @param id the id of the entity.
     */
    void deleteByAdmin(Long id);
}
