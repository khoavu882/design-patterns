package com.malu.base.gift.service.impl;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.domain.enumeration.EnumErrors;
import com.malu.base.gift.repository.GiftLangExtRepository;
import com.malu.base.gift.repository.GiftLangRepository;
import com.malu.base.gift.service.GiftLangExtService;
import com.malu.base.gift.service.dto.GiftLangExtDTO;
import com.malu.base.gift.service.mapper.GiftLangExtMapper;
import com.malu.base.gift.service.mapper.GiftLangMapper;
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
 * Time: 23:21
 */

@Service
@Transactional
@Qualifier(ApplicationConstant.EXTEND)
@Slf4j
public class GiftLangExtServiceImpl extends GiftLangServiceImpl implements GiftLangExtService {

    @Autowired
    @Qualifier(ApplicationConstant.EXTEND)
    private GiftLangExtRepository giftLangExtRepository;

    @Autowired
    private GiftLangExtMapper giftLangExtMapper;

    public GiftLangExtServiceImpl(GiftLangRepository giftLangRepository, GiftLangMapper giftLangMapper) {
        super(giftLangRepository, giftLangMapper);
    }

    @Override
    public GiftLang save(GiftLang giftLang) {
        return giftLangExtRepository.save(giftLang);
    }

    @Override
    public GiftLang create(GiftLangExtDTO giftLangExtDTO) {
        GiftLang giftLang = giftLangExtMapper.toEntity(giftLangExtDTO);
        return save(giftLang);
    }

    @Override
    public GiftLang update(GiftLangExtDTO giftLangExtDTO) {
        findOneByLanguageCodeAndGiftId(giftLangExtDTO.getLangCode(), giftLangExtDTO.getGiftId());
        return null;
    }

    @Override
    public GiftLang findOneByLanguageCodeAndGiftId(String languageCode, Long gitId) {
        Optional<GiftLang> giftLangOptional = giftLangExtRepository.findByLangCodeAndGiftId(languageCode, gitId);
        if(giftLangOptional.isEmpty()) throw new BadRequestAlertException(EnumErrors.GIFT_LANG_NOT_FOUND);
        return giftLangOptional.get();
    }
}
