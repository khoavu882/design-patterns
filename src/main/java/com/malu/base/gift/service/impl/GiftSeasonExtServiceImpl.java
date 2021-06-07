package com.malu.base.gift.service.impl;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.domain.enumeration.EnumErrors;
import com.malu.base.gift.repository.GiftSeasonExtRepository;
import com.malu.base.gift.repository.GiftSeasonRepository;
import com.malu.base.gift.service.GiftSeasonExtService;
import com.malu.base.gift.service.dto.GiftSeasonExtDTO;
import com.malu.base.gift.service.mapper.GiftProviderExtMapper;
import com.malu.base.gift.service.mapper.GiftSeasonExtMapper;
import com.malu.base.gift.service.mapper.GiftSeasonMapper;
import com.malu.base.gift.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 03/06/2021
 * Time: 23:24
 */

@Service
@Transactional
@Qualifier(ApplicationConstant.EXTEND)
@Slf4j
public class GiftSeasonExtServiceImpl extends GiftSeasonServiceImpl implements GiftSeasonExtService {

    @Autowired
    @Qualifier(ApplicationConstant.EXTEND)
    private GiftSeasonExtRepository giftSeasonExtRepository;

    @Autowired
    private GiftSeasonExtMapper giftSeasonExtMapper;

    @Autowired
    private GiftProviderExtMapper giftProviderExtMapper;

    public GiftSeasonExtServiceImpl(GiftSeasonRepository giftSeasonRepository, GiftSeasonMapper giftSeasonMapper) {
        super(giftSeasonRepository, giftSeasonMapper);
    }

    @Override
    public GiftSeason save(GiftSeason giftSeason) {
        return giftSeasonExtRepository.save(giftSeason);
    }

    @Override
    public GiftSeason create(GiftSeasonExtDTO giftSeasonExtDTO) {
        log.debug("Request to create GiftSeason");
        GiftSeason giftSeason = giftSeasonExtMapper.toEntity(giftSeasonExtDTO);
        return save(giftSeason);
    }

    @Override
    public GiftSeason update(GiftSeasonExtDTO giftSeasonExtDTO) {
        log.debug("Request to update GiftSeason");
        if(giftSeasonExtDTO.getId() == null) throw new BadRequestAlertException(EnumErrors.GIFT_SEASON_NOT_FOUND);
        GiftSeason giftSeason = findOneById(giftSeasonExtDTO.getId());
        giftSeason.setName(giftSeasonExtDTO.getName());
        giftSeason.setDescription(giftSeasonExtDTO.getDescription());
        giftSeason.setIcon(giftSeasonExtDTO.getIcon());
        giftSeason.setGiftProvider(giftProviderExtMapper.toEntity(giftSeasonExtDTO.getGiftProvider()));
        return save(giftSeason);
    }

    @Override
    public Page<GiftSeason> findAllWithFilter(String keyword, Pageable pageable) {
        return giftSeasonExtRepository.findAllWithFilter(keyword, pageable);
    }

    @Override
    public GiftSeason findOneById(Long id) {
        log.debug("Request to get GiftSeason by ID");
        Optional<GiftSeason> optionalGiftSeason = giftSeasonExtRepository.findById(id);
        if(optionalGiftSeason.isEmpty()) throw new BadRequestAlertException(EnumErrors.GIFT_SEASON_NOT_FOUND);
        return optionalGiftSeason.get();
    }
}
