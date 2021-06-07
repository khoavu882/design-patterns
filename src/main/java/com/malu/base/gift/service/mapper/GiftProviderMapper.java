package com.malu.base.gift.service.mapper;

import com.malu.base.gift.domain.*;
import com.malu.base.gift.service.dto.GiftProviderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GiftProvider} and its DTO {@link GiftProviderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GiftProviderMapper extends EntityMapper<GiftProviderDTO, GiftProvider> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GiftProviderDTO toDtoId(GiftProvider giftProvider);
}
