package com.malu.base.gift.consumer.service.impl;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.repository.GiftProviderConsumerRepository;
import com.malu.base.gift.consumer.service.GiftProviderConsumerService;
import com.malu.base.gift.consumer.web.rest.vm.GiftProviderConsumerVM;
import com.malu.base.gift.consumer.web.rest.vm.mapper.GiftProviderConsumerVMMapper;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.repository.GiftProviderRepository;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;
import com.malu.base.gift.service.impl.GiftProviderExtServiceImpl;
import com.malu.base.gift.service.mapper.GiftProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 04/06/2021
 * Time: 21:28
 */

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
public class GiftProviderConsumerServiceImpl extends GiftProviderExtServiceImpl implements GiftProviderConsumerService {

    @Autowired
    @Qualifier(ApplicationConstant.CONSUMER)
    private GiftProviderConsumerRepository giftProviderConsumerRepository;

    @Autowired
    private GiftProviderConsumerVMMapper giftProviderConsumerVMMapper;

    public GiftProviderConsumerServiceImpl(GiftProviderRepository giftProviderRepository, GiftProviderMapper giftProviderMapper) {
        super(giftProviderRepository, giftProviderMapper);
    }

    @Override
    public GiftProviderConsumerVM createByConsumer(GiftProviderExtDTO giftProviderExtDTO) {
        return giftProviderConsumerVMMapper.toDto(this.create(giftProviderExtDTO));
    }

    @Override
    public GiftProviderConsumerVM updateByConsumer(GiftProviderExtDTO giftProviderExtDTO) {
        return giftProviderConsumerVMMapper.toDto(this.update(giftProviderExtDTO));
    }

    @Override
    public Page<GiftProviderConsumerVM> findAllWithFilterByConsumer(String keyword, ActionStatus status, Pageable pageable) {
        return findAllWithFilter(keyword, status, pageable).map(giftProviderConsumerVMMapper::toDto);
    }

    @Override
    public GiftProviderConsumerVM findOneByConsumer(Long id) {
        return giftProviderConsumerVMMapper.toDto(this.findOneById(id));
    }

    @Override
    @Transactional
    public void activateByConsumer(Long id) {
        GiftProvider giftProvider = findOneById(id);
        giftProvider.setStatus(ActionStatus.ACTIVATED);
        save(giftProvider);
    }

    @Override
    @Transactional
    public void deactivateByConsumer(Long id) {
        GiftProvider giftProvider = findOneById(id);
        giftProvider.setStatus(ActionStatus.DEACTIVATED);
        save(giftProvider);
    }

    @Override
    @Transactional
    public void deleteByConsumer(Long id) {
        GiftProvider giftProvider = findOneById(id);
        giftProvider.setStatus(ActionStatus.DELETED);
        save(giftProvider);
    }
}
