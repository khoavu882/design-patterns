package com.malu.base.gift.admin.service.mapper;

import com.malu.base.gift.admin.service.dto.GiftLangAdminDTO;
import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.service.dto.GiftLangDTO;
import com.malu.base.gift.service.dto.GiftLangExtDTO;
import com.malu.base.gift.service.mapper.EntityMapper;
import com.malu.base.gift.service.mapper.GiftExtMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link GiftLang} and its DTO {@link GiftLangAdminDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftAdminDTOMapper.class })
public interface GiftLangAdminDTOMapper extends EntityMapper<GiftLangAdminDTO, GiftLang> {

}
