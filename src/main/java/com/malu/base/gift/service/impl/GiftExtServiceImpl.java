package com.malu.base.gift.service.impl;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.domain.enumeration.EnumErrors;
import com.malu.base.gift.repository.GiftExtRepository;
import com.malu.base.gift.repository.GiftRepository;
import com.malu.base.gift.service.GiftExtService;
import com.malu.base.gift.service.dto.GiftExtDTO;
import com.malu.base.gift.service.mapper.GiftExtMapper;
import com.malu.base.gift.service.mapper.GiftMapper;
import com.malu.base.gift.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:20
 */

@Service
@Transactional
@Qualifier(ApplicationConstant.EXTEND)
@Slf4j
public class GiftExtServiceImpl extends GiftServiceImpl implements GiftExtService {

    @Autowired
    @Qualifier(ApplicationConstant.EXTEND)
    private GiftExtRepository giftExtRepository;

    @Autowired
    private GiftExtMapper giftExtMapper;

    public GiftExtServiceImpl(GiftRepository giftRepository, GiftMapper giftMapper) {
        super(giftRepository, giftMapper);
    }

    @Override
    public Gift save(Gift gift) {
        return giftExtRepository.saveAndFlush(gift);
    }

    @Override
    public Gift create(GiftExtDTO giftExtDTO) {
//        Optional<GiftLangExtDTO> optDefaultLanguage = giftExtDTO.getLanguages().stream()
//            .filter(lang -> ApplicationConstant.LANGUAGE_DEFAULT.equals(lang.getLangCode())).findFirst();
//        if (optDefaultLanguage.isPresent()) {
//            log.debug("Default language is {}", optDefaultLanguage.get());
//            giftExtDTO.setName(optDefaultLanguage.get().getName());
//            giftExtDTO.setDescription(optDefaultLanguage.get().getDescription());
//        } else {
//            throw new BadRequestAlertException(EnumErrors.GIFT_DEFAULT_LANGUAGE_EMPTY);
//        }
//        giftExtDTO.setLanguages(new ArrayList<>());
        Gift gift = giftExtMapper.toEntity(giftExtDTO);
        return save(gift);
    }

    @Override
    public Gift update(GiftExtDTO giftExtDTO) {
        if(giftExtDTO.getId() == null) throw new BadRequestAlertException(EnumErrors.GIFT_NOT_FOUND);
        Gift gift = findOneById(giftExtDTO.getId());
        Gift newGift = giftExtMapper.toEntity(giftExtDTO);
        newGift.setId(gift.getId());
        newGift.setLanguages(gift.getLanguages());
        newGift.setStatus(gift.getStatus());
        return save(newGift);
    }

    @Override
    public Gift findOneById(Long id) {
        Optional<Gift> optionalGift = giftExtRepository.findById(id);
        if(optionalGift.isEmpty()) throw new BadRequestAlertException(EnumErrors.GIFT_NOT_FOUND);
        return optionalGift.get();
    }
}
