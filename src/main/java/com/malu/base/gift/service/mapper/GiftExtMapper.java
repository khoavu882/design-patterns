package com.malu.base.gift.service.mapper;

import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.dto.GiftExtDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Gift} and its DTO {@link GiftExtDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftSeasonExtMapper.class, GiftProviderExtMapper.class })
public interface GiftExtMapper extends EntityMapper<GiftExtDTO, Gift> {
}
