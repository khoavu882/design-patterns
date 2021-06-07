package com.malu.base.gift.service.mapper;

import com.malu.base.gift.domain.*;
import com.malu.base.gift.service.dto.GiftSeasonDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GiftSeason} and its DTO {@link GiftSeasonDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftProviderMapper.class })
public interface GiftSeasonMapper extends EntityMapper<GiftSeasonDTO, GiftSeason> {
    @Mapping(target = "giftProvider", source = "giftProvider", qualifiedByName = "id")
    GiftSeasonDTO toDto(GiftSeason s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GiftSeasonDTO toDtoId(GiftSeason giftSeason);
}
