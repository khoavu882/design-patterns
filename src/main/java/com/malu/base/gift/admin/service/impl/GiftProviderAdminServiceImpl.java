package com.malu.base.gift.admin.service.impl;

import com.malu.base.gift.admin.repository.GiftProviderAdminRepository;
import com.malu.base.gift.admin.service.GiftProviderAdminService;
import com.malu.base.gift.admin.web.rest.vm.GiftProviderAdminVM;
import com.malu.base.gift.admin.web.rest.vm.mapper.GiftProviderAdminVMMapper;
import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.repository.GiftProviderRepository;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;
import com.malu.base.gift.service.impl.GiftProviderExtServiceImpl;
import com.malu.base.gift.service.mapper.GiftProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Khoa Vu.
 * Mail: khoa.vu@vslsoft.com
 * Date: 04/06/2021
 * Time: 21:28
 */

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
public class GiftProviderAdminServiceImpl extends GiftProviderExtServiceImpl implements GiftProviderAdminService {

    @Autowired
    @Qualifier(ApplicationConstant.ADMIN)
    private GiftProviderAdminRepository giftProviderAdminRepository;

    @Autowired
    private GiftProviderAdminVMMapper giftProviderAdminVMMapper;

    public GiftProviderAdminServiceImpl(GiftProviderRepository giftProviderRepository, GiftProviderMapper giftProviderMapper) {
        super(giftProviderRepository, giftProviderMapper);
    }

    @Override
    public GiftProviderAdminVM createByAdmin(GiftProviderExtDTO giftProviderExtDTO) {
        return giftProviderAdminVMMapper.toDto(this.create(giftProviderExtDTO));
    }

    @Override
    public GiftProviderAdminVM updateByAdmin(GiftProviderExtDTO giftProviderExtDTO) {
        return giftProviderAdminVMMapper.toDto(this.update(giftProviderExtDTO));
    }

    @Override
    public Page<GiftProviderAdminVM> findAllWithFilterByAdmin(String keyword, ActionStatus status, Pageable pageable) {
        return findAllWithFilter(keyword, status, pageable).map(giftProviderAdminVMMapper::toDto);
    }

    @Override
    public GiftProviderAdminVM findOneByAdmin(Long id) {
        return giftProviderAdminVMMapper.toDto(this.findOneById(id));
    }

    @Override
    @Transactional
    public void activateByAdmin(Long id) {
        GiftProvider giftProvider = findOneById(id);
        giftProvider.setStatus(ActionStatus.ACTIVATED);
        save(giftProvider);
    }

    @Override
    @Transactional
    public void deactivateByAdmin(Long id) {
        GiftProvider giftProvider = findOneById(id);
        giftProvider.setStatus(ActionStatus.DEACTIVATED);
        save(giftProvider);
    }

    @Override
    @Transactional
    public void deleteByAdmin(Long id) {
        GiftProvider giftProvider = findOneById(id);
        giftProvider.setStatus(ActionStatus.DELETED);
        save(giftProvider);
    }
}
