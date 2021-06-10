package com.malu.base.gift.consumer.service.impl;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.service.GiftProviderConsumerService;
import com.malu.base.gift.consumer.service.GiftSeasonConsumerService;
import com.malu.base.gift.consumer.service.dto.GiftSeasonConsumerDTO;
import com.malu.base.gift.consumer.web.rest.vm.GiftSeasonConsumerVM;
import com.malu.base.gift.consumer.web.rest.vm.mapper.GiftSeasonConsumerVMMapper;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.repository.GiftSeasonRepository;
import com.malu.base.gift.service.dto.GiftSeasonExtDTO;
import com.malu.base.gift.service.impl.GiftSeasonExtServiceImpl;
import com.malu.base.gift.service.mapper.GiftProviderExtMapper;
import com.malu.base.gift.service.mapper.GiftSeasonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 05/06/2021
 * Time: 00:32
 */

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
@Slf4j
public class GiftSeasonConsumerServiceImpl extends GiftSeasonExtServiceImpl implements GiftSeasonConsumerService {

    @Autowired
    @Qualifier(ApplicationConstant.CONSUMER)
    private GiftProviderConsumerService giftProviderConsumerService;

    @Autowired
    private GiftSeasonConsumerVMMapper giftSeasonConsumerVMMapper;

    @Autowired
    private GiftProviderExtMapper giftProviderExtMapper;

    public GiftSeasonConsumerServiceImpl(GiftSeasonRepository giftSeasonRepository, GiftSeasonMapper giftSeasonMapper) {
        super(giftSeasonRepository, giftSeasonMapper);
    }

    @Override
    public GiftSeasonConsumerVM createByConsumer(GiftSeasonConsumerDTO giftSeasonConsumerDTO) {
        GiftSeasonExtDTO giftSeasonExtDTO = intiGiftSeasonExtDTO(giftSeasonConsumerDTO);
        return giftSeasonConsumerVMMapper.toDto(create(giftSeasonExtDTO));
    }

    @Override
    public GiftSeasonConsumerVM updateByConsumer(GiftSeasonConsumerDTO giftSeasonConsumerDTO) {
        GiftSeasonExtDTO giftSeasonExtDTO = intiGiftSeasonExtDTO(giftSeasonConsumerDTO);
        return giftSeasonConsumerVMMapper.toDto(update(giftSeasonExtDTO));
    }

    private GiftSeasonExtDTO intiGiftSeasonExtDTO(GiftSeasonConsumerDTO giftSeasonConsumerDTO) {
        GiftProvider giftProvider = new GiftProvider();
        GiftSeasonExtDTO giftSeasonExtDTO = new GiftSeasonExtDTO();
        giftSeasonExtDTO.setId(giftSeasonConsumerDTO.getId());
        giftSeasonExtDTO.setName(giftSeasonConsumerDTO.getName());
        giftSeasonExtDTO.setDescription(giftSeasonConsumerDTO.getDescription());
        giftSeasonExtDTO.setIcon(giftSeasonConsumerDTO.getDescription());
        if (giftSeasonConsumerDTO.getGiftProviderId() != null) {
            giftProvider =
                giftProviderConsumerService.findOneById(
                    giftSeasonConsumerDTO.getGiftProviderId() == null ? 1L : giftSeasonConsumerDTO.getGiftProviderId()
                );
            giftSeasonExtDTO.setGiftProvider(giftProviderExtMapper.toDto(giftProvider));
        }
        return giftSeasonExtDTO;
    }

    @Override
    public Page<GiftSeasonConsumerVM> findAllWithFilterByConsumer(String keyword, Pageable pageable) {
        return findAllWithFilter(keyword, pageable).map(giftSeasonConsumerVMMapper::toDto);
    }

    @Override
    public GiftSeasonConsumerVM findOneByConsumer(Long id) {
        return giftSeasonConsumerVMMapper.toDto(findOneById(id));
    }
}
