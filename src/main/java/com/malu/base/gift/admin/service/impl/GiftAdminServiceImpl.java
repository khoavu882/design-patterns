package com.malu.base.gift.admin.service.impl;

import com.google.gson.Gson;
import com.malu.base.gift.admin.repository.GiftAdminRepository;
import com.malu.base.gift.admin.service.GiftAdminService;
import com.malu.base.gift.admin.service.GiftLangAdminService;
import com.malu.base.gift.admin.service.GiftSeasonAdminService;
import com.malu.base.gift.admin.service.dto.GiftAdminDTO;
import com.malu.base.gift.admin.service.dto.GiftLangAdminDTO;
import com.malu.base.gift.admin.service.mapper.GiftLangAdminDTOMapper;
import com.malu.base.gift.admin.web.rest.vm.GiftAdminVM;
import com.malu.base.gift.admin.web.rest.vm.mapper.GiftAdminVMMapper;
import com.malu.base.gift.constant.ApplicationConstant;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
@Slf4j
public class GiftAdminServiceImpl extends GiftExtServiceImpl implements GiftAdminService {

    @Autowired
    @Qualifier(ApplicationConstant.ADMIN)
    private GiftAdminRepository giftAdminRepository;

    @Autowired
    @Qualifier(ApplicationConstant.ADMIN)
    private GiftLangAdminService giftLangAdminService;

    @Autowired
    @Qualifier(ApplicationConstant.ADMIN)
    private GiftSeasonAdminService giftSeasonAdminService;

    @Autowired
    private GiftAdminVMMapper giftAdminVMMapper;

    @Autowired
    private GiftSeasonExtMapper giftSeasonExtMapper;

    @Autowired
    private GiftLangAdminDTOMapper giftLangAdminDTOMapper;

    @Autowired
    private GiftLangExtMapper giftLangExtMapper;

    public GiftAdminServiceImpl(GiftRepository giftRepository, GiftMapper giftMapper) {
        super(giftRepository, giftMapper);
    }

    @Override
    public GiftAdminVM createByAdmin(GiftAdminDTO giftAdminDTO) {
        GiftExtDTO giftExtDTO = initGift(giftAdminDTO);
        Gift gift = create(giftExtDTO);
        giftLangAdminService.translateGift(giftAdminDTO.getLanguages(), gift, false);
        return giftAdminVMMapper.toDto(gift);
    }

    @Override
    public GiftAdminVM updateByAdmin(GiftAdminDTO giftAdminDTO) {
        GiftExtDTO giftExtDTO = initGift(giftAdminDTO);
        giftExtDTO.setId(giftAdminDTO.getId());
        Gift gift = update(giftExtDTO);
            giftLangAdminService.translateGift(giftAdminDTO.getLanguages(), gift, true);
        return giftAdminVMMapper.toDto(gift);
    }

    private GiftExtDTO initGift(GiftAdminDTO giftAdminDTO) {
        Gson gson = new Gson();
        GiftExtDTO giftExtDTO = new GiftExtDTO();
        Optional<GiftLangAdminDTO> optDefaultLanguage = giftAdminDTO.getLanguages().stream()
            .filter(lang -> lang.getLangCode().equals(ApplicationConstant.LANGUAGE_DEFAULT)).findFirst();
        if (optDefaultLanguage.isPresent()) {
            giftExtDTO.setCode(giftAdminDTO.getCode());
            giftExtDTO.setName(optDefaultLanguage.get().getName());
            giftExtDTO.setDescription(optDefaultLanguage.get().getDescription());
            giftExtDTO.setIcon(giftAdminDTO.getIcon());
            giftExtDTO.setMediaPath(giftAdminDTO.getMediaPath());
            giftExtDTO.setPrice(giftAdminDTO.getPrice());
            giftExtDTO.setOriginalPrice(giftAdminDTO.getOriginalPrice());
            giftExtDTO.setPublishDate(giftAdminDTO.getPublishDate());
            giftExtDTO.setStartDate(giftAdminDTO.getStartDate());
            giftExtDTO.setExpireDate(giftAdminDTO.getExpireDate());
            giftExtDTO.setUseGuide(giftAdminDTO.getUseGuide());
            giftExtDTO.setTerms(giftAdminDTO.getTerms());
            giftExtDTO.setTags(gson.toJson(giftAdminDTO.getTags()));
            giftExtDTO.setStatus(EnumGiftStatus.ACTIVATED);
//            giftExtDTO.setLanguages(giftLangExtMapper.toDto(giftLangAdminDTOMapper.toEntity(giftAdminDTO.getLanguages())));
//            GiftSeason giftSeason = giftSeasonAdminService.findOneById(giftAdminDTO.getGiftSeasonId() == null ? 1L : giftAdminDTO.getGiftSeasonId());
//            giftExtDTO.setGiftSeason(giftSeasonExtMapper.toDto(giftSeason));
        } else {
            throw new BadRequestAlertException(EnumErrors.GIFT_DEFAULT_LANGUAGE_EMPTY);
        }
        return giftExtDTO;
    }

    @Override
    public Page<GiftAdminVM> findAllWithFilterByAdmin(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public GiftAdminVM findOneByAdmin(Long id) {
        return giftAdminVMMapper.toDto(findOneById(id));
    }
}
