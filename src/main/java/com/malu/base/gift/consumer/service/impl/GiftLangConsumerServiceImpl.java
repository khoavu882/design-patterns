package com.malu.base.gift.consumer.service.impl;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.service.GiftLangConsumerService;
import com.malu.base.gift.consumer.service.dto.GiftLangConsumerDTO;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.repository.GiftLangRepository;
import com.malu.base.gift.service.dto.GiftLangExtDTO;
import com.malu.base.gift.service.impl.GiftLangExtServiceImpl;
import com.malu.base.gift.service.mapper.GiftExtMapper;
import com.malu.base.gift.service.mapper.GiftLangMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Qualifier(ApplicationConstant.CONSUMER)
@Slf4j
public class GiftLangConsumerServiceImpl extends GiftLangExtServiceImpl implements GiftLangConsumerService {

    @Autowired
    private GiftExtMapper giftExtMapper;

    public GiftLangConsumerServiceImpl(GiftLangRepository giftLangRepository, GiftLangMapper giftLangMapper) {
        super(giftLangRepository, giftLangMapper);
    }

    @Override
    public void translateGift(List<GiftLangConsumerDTO> giftLangConsumerDTOList, Gift gift, boolean isCreate) {
        giftLangConsumerDTOList.forEach(
            giftLangConsumerDTO -> {
                //May be use mapper to convert
                GiftLangExtDTO giftLangExtDTO = new GiftLangExtDTO();
                giftLangExtDTO.setName(giftLangConsumerDTO.getName());
                giftLangExtDTO.setDescription(giftLangConsumerDTO.getDescription());
                giftLangExtDTO.setLangCode(giftLangConsumerDTO.getLangCode());
                giftLangExtDTO.setTerms(giftLangConsumerDTO.getTerms());
                giftLangExtDTO.setUseGuide(giftLangConsumerDTO.getUseGuide());
                giftLangExtDTO.setGift(giftExtMapper.toDto(gift));
                giftLangExtDTO.setStatus(ActionStatus.ACTIVATED);
                GiftLang giftLang = null;
                if (isCreate) giftLang = this.create(giftLangExtDTO); else giftLang = this.update(giftLangExtDTO);
                gift.getLanguages().add(giftLang);
            }
        );
    }
}
