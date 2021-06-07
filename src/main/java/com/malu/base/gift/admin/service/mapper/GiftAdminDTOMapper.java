package com.malu.base.gift.admin.service.mapper;

import com.malu.base.gift.admin.service.dto.GiftAdminDTO;
import com.malu.base.gift.admin.service.mapper.GiftSeasonAdminDTOMapper;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.dto.GiftExtDTO;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Gift} and its DTO {@link GiftAdminDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftSeasonAdminDTOMapper.class })
public interface GiftAdminDTOMapper extends EntityMapper<GiftAdminDTO, Gift> {
}
