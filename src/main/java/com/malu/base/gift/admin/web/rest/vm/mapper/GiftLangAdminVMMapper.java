package com.malu.base.gift.admin.web.rest.vm.mapper;

import com.malu.base.gift.admin.web.rest.vm.GiftLangAdminVM;
import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.service.dto.GiftLangDTO;
import com.malu.base.gift.service.dto.GiftLangExtDTO;
import com.malu.base.gift.service.mapper.EntityMapper;
import com.malu.base.gift.service.mapper.GiftExtMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link GiftLang} and its DTO {@link GiftLangAdminVM}.
 */
@Mapper(componentModel = "spring", uses = { GiftAdminVMMapper.class })
public interface GiftLangAdminVMMapper extends EntityMapper<GiftLangAdminVM, GiftLang> {
}
