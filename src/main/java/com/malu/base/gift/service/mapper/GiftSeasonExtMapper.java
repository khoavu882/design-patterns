package com.malu.base.gift.service.mapper;

import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.service.dto.GiftSeasonExtDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link GiftSeason} and its DTO {@link GiftSeasonExtDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftProviderExtMapper.class })
public interface GiftSeasonExtMapper extends EntityMapper<GiftSeasonExtDTO, GiftSeason> {
}
