package com.malu.base.gift.service;

import com.malu.base.gift.admin.web.rest.vm.GiftProviderAdminVM;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:18
 */
public interface GiftProviderExtService extends GiftProviderService {

    /**
     * Save a giftProvider.
     *
     * @param giftProvider the entity to save.
     * @return the persisted entity.
     */
    GiftProvider save(GiftProvider giftProvider);

    /**
     * Save a giftProvider.
     *
     * @param giftProviderExtDTO the entity to save.
     * @return the persisted entity.
     */
    GiftProvider create(GiftProviderExtDTO giftProviderExtDTO);

    /**
     * Save a giftProvider.
     *
     * @param giftProviderExtDTO the entity to save.
     * @return the persisted entity.
     */
    GiftProvider update(GiftProviderExtDTO giftProviderExtDTO);

    /**
     * Get all the giftProviders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GiftProvider> findAllWithFilter(String keyword, ActionStatus status, Pageable pageable);

    /**
     * Get the "id" giftProvider.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    GiftProvider findOneById(Long id);
}
