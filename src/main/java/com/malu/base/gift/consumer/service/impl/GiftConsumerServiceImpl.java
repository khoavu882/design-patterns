package com.malu.base.gift.consumer.service.impl;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.repository.GiftConsumerRepository;
import com.malu.base.gift.consumer.service.GiftConsumerService;
import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.consumer.web.rest.vm.GiftOwnerConsumerVM;
import com.malu.base.gift.consumer.web.rest.vm.mapper.GiftConsumerVMMapper;
import com.malu.base.gift.consumer.web.rest.vm.mapper.GiftOwnerConsumerVMMapper;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.domain.enumeration.EnumGiftStatus;
import com.malu.base.gift.repository.GiftRepository;
import com.malu.base.gift.security.SecurityUtils;
import com.malu.base.gift.service.GiftExtService;
import com.malu.base.gift.service.impl.GiftExtServiceImpl;
import com.malu.base.gift.service.mapper.GiftMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
@Slf4j
public class GiftConsumerServiceImpl extends GiftExtServiceImpl implements GiftConsumerService {

    @Autowired
    @Qualifier(ApplicationConstant.CONSUMER)
    private GiftConsumerRepository giftConsumerRepository;

    @Autowired
    @Qualifier(ApplicationConstant.EXTEND)
    private GiftExtService giftExtService;

    @Autowired
    private GiftConsumerVMMapper giftConsumerVMMapper;

    @Autowired
    private GiftOwnerConsumerVMMapper giftOwnerConsumerVMMapper;

    public GiftConsumerServiceImpl(GiftRepository giftRepository, GiftMapper giftMapper) {
        super(giftRepository, giftMapper);
    }

    @Override
    public Page<GiftConsumerVM> findAllWithFilterByConsumer(String keyword, Pageable pageable) {
        return giftExtService.findAllWithFilter(keyword, EnumGiftStatus.ACTIVATED, pageable).map(giftConsumerVMMapper::toDto);
    }

    @Override
    public Page<GiftOwnerConsumerVM> findAllWithFilterByOwner(String keyword, Pageable pageable) {
        return giftConsumerRepository.findAllWithFilterByOwner(SecurityUtils.getCurrentUserLogin().get(), keyword, EnumGiftStatus.ACTIVATED, pageable).map(giftOwnerConsumerVMMapper::toDto);
    }

    @Override
    public GiftConsumerVM findOneByHashCodeByConsumer(String hashCode) {
        return giftConsumerVMMapper.toDto(findOneByHashCode(hashCode));
    }

    @Override
    public List<GiftConsumerVM> findByListHashCodeByConsumer(List<String> listHashCode) {
        return giftConsumerVMMapper.toDto(giftConsumerRepository.findByListHashCodeByConsumer(listHashCode));
    }

    @Override
    public GiftOwnerConsumerVM findOneByHashCodeByOwner(String hashCode) {
        return giftOwnerConsumerVMMapper.toDto(findOneByHashCode(hashCode));
    }
}
