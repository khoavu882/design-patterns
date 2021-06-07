package com.malu.base.gift.service.impl;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.domain.enumeration.EnumErrors;
import com.malu.base.gift.repository.GiftProviderExtRepository;
import com.malu.base.gift.repository.GiftProviderRepository;
import com.malu.base.gift.service.GiftProviderExtService;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;
import com.malu.base.gift.service.mapper.GiftProviderExtMapper;
import com.malu.base.gift.service.mapper.GiftProviderMapper;
import com.malu.base.gift.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:23
 */

@Service
@Transactional
@Qualifier(ApplicationConstant.EXTEND)
@Slf4j
public class GiftProviderExtServiceImpl extends GiftProviderServiceImpl implements GiftProviderExtService {

    @Autowired
    @Qualifier(ApplicationConstant.EXTEND)
    private GiftProviderExtRepository giftProviderExtRepository;

    @Autowired
    private GiftProviderExtMapper giftProviderExtMapper;

    public GiftProviderExtServiceImpl(GiftProviderRepository giftProviderRepository, GiftProviderMapper giftProviderMapper) {
        super(giftProviderRepository, giftProviderMapper);
    }

    @Override
    public GiftProvider save(GiftProvider giftProvider) {
        return giftProviderExtRepository.save(giftProvider);
    }

    @Override
    @Transactional
    public GiftProvider create(GiftProviderExtDTO giftProviderExtDTO) {
        log.debug("Request to create GiftProvider");
        GiftProvider giftProvider = giftProviderExtMapper.toEntity(giftProviderExtDTO);
        giftProvider.setStatus(ActionStatus.ACTIVATED);
        return save(giftProvider);
    }

    @Override
    @Transactional
    public GiftProvider update(GiftProviderExtDTO giftProviderExtDTO) {
        log.debug("Request to update GiftProvider");
        if(giftProviderExtDTO.getId() == null) throw new BadRequestAlertException(EnumErrors.GIFT_PROVIDER_NOT_FOUND);
        GiftProvider giftProvider = findOneById(giftProviderExtDTO.getId());
        giftProvider.setCode(giftProviderExtDTO.getCode());
        giftProvider.setName(giftProviderExtDTO.getName());
        giftProvider.setWebsite(giftProviderExtDTO.getWebsite());
        giftProvider.setIcon(giftProviderExtDTO.getIcon());
        return save(giftProvider);
    }

    @Override
    public Page<GiftProvider> findAllWithFilter(String keyword, ActionStatus status, Pageable pageable) {
        return giftProviderExtRepository.findAllWithFilter(keyword, status, pageable);
    }

    @Override
    public GiftProvider findOneById(Long id) {
        log.debug("Request to get GiftProvider by ID");
        Optional<GiftProvider> optionalGiftProvider = giftProviderExtRepository.findById(id);
        if(optionalGiftProvider.isEmpty()) throw new BadRequestAlertException(EnumErrors.GIFT_PROVIDER_NOT_FOUND);
        return optionalGiftProvider.get();
    }
}
