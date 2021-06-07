package com.malu.base.gift.admin.web.rest.vm.mapper;

import com.malu.base.gift.admin.web.rest.vm.GiftAdminVM;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.dto.GiftExtDTO;
import com.malu.base.gift.service.mapper.EntityMapper;
import com.malu.base.gift.service.mapper.GiftSeasonExtMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Gift} and its DTO {@link GiftAdminVM}.
 */
//@Mapper(componentModel = "spring", uses = { GiftSeasonAdminVMMapper.class, GiftLangAdminVMMapper.class })
@Mapper(componentModel = "spring", uses = { GiftLangAdminVMMapper.class })
public interface GiftAdminVMMapper extends EntityMapper<GiftAdminVM, Gift> {
}
