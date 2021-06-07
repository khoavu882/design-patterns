package com.malu.base.gift.service.mapper;

import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link GiftProvider} and its DTO {@link GiftProviderExtDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GiftProviderExtMapper extends EntityMapper<GiftProviderExtDTO, GiftProvider> {
}
