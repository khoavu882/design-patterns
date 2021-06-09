package com.malu.base.gift.consumer.service;

import com.malu.base.gift.admin.service.dto.GiftAdminDTO;
import com.malu.base.gift.admin.web.rest.vm.GiftAdminVM;
import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.service.GiftExtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:19
 */
public interface GiftConsumerService extends GiftExtService {

    /**
     * Get the "hashCode" gift.
     *
     * @param hashCode the hashCode of the entity.
     * @return the entity.
     */
    GiftConsumerVM consumerFindOneByHashCode(String hashCode);
}
