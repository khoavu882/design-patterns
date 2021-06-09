package com.malu.base.gift.service.mapper;

import com.malu.base.gift.domain.*;
import com.malu.base.gift.service.dto.GiftDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Gift} and its DTO {@link GiftDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftSeasonMapper.class, GiftProviderMapper.class })
public interface GiftMapper extends EntityMapper<GiftDTO, Gift> {
    @Mapping(target = "giftSeason", source = "giftSeason", qualifiedByName = "id")
    @Mapping(target = "giftProvider", source = "giftProvider", qualifiedByName = "id")
    GiftDTO toDto(Gift s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GiftDTO toDtoId(Gift gift);
}
