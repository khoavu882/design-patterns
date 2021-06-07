package com.malu.base.gift.admin.service.impl;

import com.malu.base.gift.admin.service.GiftProviderAdminService;
import com.malu.base.gift.admin.service.GiftSeasonAdminService;
import com.malu.base.gift.admin.service.dto.GiftSeasonAdminDTO;
import com.malu.base.gift.admin.web.rest.vm.GiftSeasonAdminVM;
import com.malu.base.gift.admin.web.rest.vm.mapper.GiftSeasonAdminVMMapper;
import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.domain.GiftSeason;
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
@Qualifier(ApplicationConstant.ADMIN)
@Slf4j
public class GiftSeasonAdminServiceImpl extends GiftSeasonExtServiceImpl implements GiftSeasonAdminService {

    @Autowired
    @Qualifier(ApplicationConstant.ADMIN)
    private GiftProviderAdminService giftProviderAdminService;

    @Autowired
    private GiftSeasonAdminVMMapper giftSeasonAdminVMMapper;

    @Autowired
    private GiftProviderExtMapper giftProviderExtMapper;

    public GiftSeasonAdminServiceImpl(GiftSeasonRepository giftSeasonRepository, GiftSeasonMapper giftSeasonMapper) {
        super(giftSeasonRepository, giftSeasonMapper);
    }

    @Override
    public GiftSeasonAdminVM createByAdmin(GiftSeasonAdminDTO giftSeasonAdminDTO) {
        GiftSeasonExtDTO giftSeasonExtDTO = intiGiftSeasonExtDTO(giftSeasonAdminDTO);
        return giftSeasonAdminVMMapper.toDto(create(giftSeasonExtDTO));
    }

    @Override
    public GiftSeasonAdminVM updateByAdmin(GiftSeasonAdminDTO giftSeasonAdminDTO) {
        GiftSeasonExtDTO giftSeasonExtDTO = intiGiftSeasonExtDTO(giftSeasonAdminDTO);
        return giftSeasonAdminVMMapper.toDto(update(giftSeasonExtDTO));
    }

    private GiftSeasonExtDTO intiGiftSeasonExtDTO(GiftSeasonAdminDTO giftSeasonAdminDTO) {
        GiftProvider giftProvider = new GiftProvider();
        GiftSeasonExtDTO giftSeasonExtDTO = new GiftSeasonExtDTO();
        giftSeasonExtDTO.setId(giftSeasonAdminDTO.getId());
        giftSeasonExtDTO.setName(giftSeasonAdminDTO.getName());
        giftSeasonExtDTO.setDescription(giftSeasonAdminDTO.getDescription());
        giftSeasonExtDTO.setIcon(giftSeasonAdminDTO.getDescription());
        if(giftSeasonAdminDTO.getGiftProviderId() != null) {
            giftProvider = giftProviderAdminService.findOneById(giftSeasonAdminDTO.getGiftProviderId() == null ? 1L : giftSeasonAdminDTO.getGiftProviderId());
            giftSeasonExtDTO.setGiftProvider(giftProviderExtMapper.toDto(giftProvider));
        }
        return giftSeasonExtDTO;
    }

    @Override
    public Page<GiftSeasonAdminVM> findAllWithFilterByAdmin(String keyword, Pageable pageable) {
        return findAllWithFilter(keyword, pageable).map(giftSeasonAdminVMMapper::toDto);
    }

    @Override
    public GiftSeasonAdminVM findOneByAdmin(Long id) {
        return giftSeasonAdminVMMapper.toDto(findOneById(id));
    }
}
