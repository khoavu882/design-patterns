package com.malu.base.gift.consumer.service.impl;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.service.GiftConsumerService;
import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.consumer.web.rest.vm.mapper.GiftConsumerVMMapper;
import com.malu.base.gift.repository.GiftRepository;
import com.malu.base.gift.service.impl.GiftExtServiceImpl;
import com.malu.base.gift.service.mapper.GiftMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
@Slf4j
public class GiftConsumerServiceImpl extends GiftExtServiceImpl implements GiftConsumerService {

    @Autowired
    private GiftConsumerVMMapper giftConsumerVMMapper;

    public GiftConsumerServiceImpl(GiftRepository giftRepository, GiftMapper giftMapper) {
        super(giftRepository, giftMapper);
    }

    @Override
    public GiftConsumerVM consumerFindOneByHashCode(String hashCode) {
        return giftConsumerVMMapper.toDto(findOneByHashCode(hashCode));
    }
}
