package com.malu.base.gift.consumer.service.impl;

import com.google.gson.Gson;
import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.repository.GiftConsumerRepository;
import com.malu.base.gift.consumer.service.GiftConsumerService;
import com.malu.base.gift.consumer.service.GiftLangConsumerService;
import com.malu.base.gift.consumer.service.GiftSeasonConsumerService;
import com.malu.base.gift.consumer.service.dto.GiftConsumerDTO;
import com.malu.base.gift.consumer.service.dto.GiftLangConsumerDTO;
import com.malu.base.gift.consumer.service.mapper.GiftLangConsumerDTOMapper;
import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.consumer.web.rest.vm.mapper.GiftConsumerVMMapper;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.domain.enumeration.EnumErrors;
import com.malu.base.gift.domain.enumeration.EnumGiftStatus;
import com.malu.base.gift.repository.GiftRepository;
import com.malu.base.gift.service.dto.GiftExtDTO;
import com.malu.base.gift.service.impl.GiftExtServiceImpl;
import com.malu.base.gift.service.mapper.GiftLangExtMapper;
import com.malu.base.gift.service.mapper.GiftMapper;
import com.malu.base.gift.service.mapper.GiftSeasonExtMapper;
import com.malu.base.gift.web.rest.errors.BadRequestAlertException;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
@Slf4j
public class GiftConsumerServiceImpl extends GiftExtServiceImpl implements GiftConsumerService {

    @Autowired
    @Qualifier(ApplicationConstant.CONSUMER)
    private GiftConsumerRepository giftConsumerRepository;

    @Autowired
    @Qualifier(ApplicationConstant.CONSUMER)
    private GiftLangConsumerService giftLangConsumerService;

    @Autowired
    @Qualifier(ApplicationConstant.CONSUMER)
    private GiftSeasonConsumerService giftSeasonConsumerService;

    @Autowired
    private GiftConsumerVMMapper giftConsumerVMMapper;

    @Autowired
    private GiftSeasonExtMapper giftSeasonExtMapper;

    @Autowired
    private GiftLangConsumerDTOMapper giftLangConsumerDTOMapper;

    @Autowired
    private GiftLangExtMapper giftLangExtMapper;

    public GiftConsumerServiceImpl(GiftRepository giftRepository, GiftMapper giftMapper) {
        super(giftRepository, giftMapper);
    }

    @Override
    public GiftConsumerVM createByConsumer(GiftConsumerDTO giftConsumerDTO) {
        GiftExtDTO giftExtDTO = initGift(giftConsumerDTO);
        Gift gift = create(giftExtDTO);
        giftLangConsumerService.translateGift(giftConsumerDTO.getLanguages(), gift, true);
        return giftConsumerVMMapper.toDto(gift);
    }

    @Override
    public GiftConsumerVM updateByConsumer(GiftConsumerDTO giftConsumerDTO) {
        GiftExtDTO giftExtDTO = initGift(giftConsumerDTO);
        giftExtDTO.setId(giftConsumerDTO.getId());
        Gift gift = update(giftExtDTO);
        giftLangConsumerService.translateGift(giftConsumerDTO.getLanguages(), gift, false);
        return giftConsumerVMMapper.toDto(gift);
    }

    private GiftExtDTO initGift(GiftConsumerDTO giftConsumerDTO) {
        Gson gson = new Gson();
        GiftExtDTO giftExtDTO = new GiftExtDTO();
        Optional<GiftLangConsumerDTO> optDefaultLanguage = giftConsumerDTO
            .getLanguages()
            .stream()
            .filter(lang -> lang.getLangCode().equals(ApplicationConstant.LANGUAGE_DEFAULT))
            .findFirst();
        if (optDefaultLanguage.isPresent()) {
            giftExtDTO.setHashCode(UUID.randomUUID().toString());
            giftExtDTO.setCode(giftConsumerDTO.getCode());
            giftExtDTO.setSerial(giftConsumerDTO.getSerial());
            giftExtDTO.setName(optDefaultLanguage.get().getName());
            giftExtDTO.setDescription(optDefaultLanguage.get().getDescription());
            giftExtDTO.setIcon(giftConsumerDTO.getIcon());
            giftExtDTO.setMediaPath(giftConsumerDTO.getMediaPath());
            giftExtDTO.setPrice(giftConsumerDTO.getPrice());
            giftExtDTO.setOriginalPrice(giftConsumerDTO.getOriginalPrice());
            giftExtDTO.setPublishDate(giftConsumerDTO.getPublishDate());
            giftExtDTO.setStartDate(giftConsumerDTO.getStartDate());
            giftExtDTO.setExpireDate(giftConsumerDTO.getExpireDate());
            giftExtDTO.setUseGuide(giftConsumerDTO.getUseGuide());
            giftExtDTO.setTerms(giftConsumerDTO.getTerms());
            giftExtDTO.setTags(gson.toJson(giftConsumerDTO.getTags()));
            giftExtDTO.setStatus(EnumGiftStatus.ACTIVATED);
            //            giftExtDTO.setLanguages(giftLangExtMapper.toDto(giftLangConsumerDTOMapper.toEntity(giftConsumerDTO.getLanguages())));
            //            GiftSeason giftSeason = giftSeasonConsumerService.findOneById(giftConsumerDTO.getGiftSeasonId() == null ? 1L : giftConsumerDTO.getGiftSeasonId());
            //            giftExtDTO.setGiftSeason(giftSeasonExtMapper.toDto(giftSeason));
        } else {
            throw new BadRequestAlertException(EnumErrors.GIFT_DEFAULT_LANGUAGE_EMPTY);
        }
        return giftExtDTO;
    }

    @Override
    public Page<GiftConsumerVM> findAllWithFilterByConsumer(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public GiftConsumerVM findOneByConsumer(Long id) {
        return giftConsumerVMMapper.toDto(findOneById(id));
    }
}
