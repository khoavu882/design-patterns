package com.malu.base.gift.admin.service.impl;

import com.malu.base.gift.admin.service.GiftLangAdminService;
import com.malu.base.gift.admin.service.dto.GiftLangAdminDTO;
import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.repository.GiftLangRepository;
import com.malu.base.gift.service.dto.GiftLangExtDTO;
import com.malu.base.gift.service.impl.GiftLangExtServiceImpl;
import com.malu.base.gift.service.mapper.GiftExtMapper;
import com.malu.base.gift.service.mapper.GiftLangMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
@Slf4j
public class GiftLangAdminServiceImpl extends GiftLangExtServiceImpl implements GiftLangAdminService {

    @Autowired
    private GiftExtMapper giftExtMapper;

    public GiftLangAdminServiceImpl(GiftLangRepository giftLangRepository, GiftLangMapper giftLangMapper) {
        super(giftLangRepository, giftLangMapper);
    }

    @Override
    public void translateGift(List<GiftLangAdminDTO> giftLangAdminDTOList, Gift gift, boolean isCreate) {

            giftLangAdminDTOList.forEach(giftLangAdminDTO -> {
                //May be use mapper to convert
                GiftLangExtDTO giftLangExtDTO = new GiftLangExtDTO();
                giftLangExtDTO.setName(giftLangAdminDTO.getName());
                giftLangExtDTO.setDescription(giftLangAdminDTO.getDescription());
                giftLangExtDTO.setLangCode(giftLangAdminDTO.getLangCode());
                giftLangExtDTO.setTerms(giftLangAdminDTO.getTerms());
                giftLangExtDTO.setUseGuide(giftLangAdminDTO.getUseGuide());
                giftLangExtDTO.setStatus(ActionStatus.ACTIVATED);
                giftLangExtDTO.setGiftId(gift.getId());
                GiftLang giftLang = null;
                if(isCreate)
                    giftLang = this.create(giftLangExtDTO);
                else
                    giftLang = this.update(giftLangExtDTO);
                giftLang.setGift(gift);
            });
    }
}
