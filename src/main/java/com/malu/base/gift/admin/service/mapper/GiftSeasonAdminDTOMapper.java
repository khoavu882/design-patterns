package com.malu.base.gift.admin.service.mapper;

import com.malu.base.gift.admin.service.dto.GiftSeasonAdminDTO;
import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.service.dto.GiftSeasonExtDTO;
import com.malu.base.gift.service.mapper.EntityMapper;
import com.malu.base.gift.service.mapper.GiftProviderExtMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link GiftSeason} and its DTO {@link GiftSeasonAdminDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftProviderAdminDTOMapper.class })
public interface GiftSeasonAdminDTOMapper extends EntityMapper<GiftSeasonAdminDTO, GiftSeason> {
}
