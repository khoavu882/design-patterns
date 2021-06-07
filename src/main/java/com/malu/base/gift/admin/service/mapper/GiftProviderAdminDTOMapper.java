package com.malu.base.gift.admin.service.mapper;

import com.malu.base.gift.admin.service.dto.GiftProviderAdminDTO;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link GiftProvider} and its DTO {@link GiftProviderAdminDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GiftProviderAdminDTOMapper extends EntityMapper<GiftProviderAdminDTO, GiftProvider> {
}
