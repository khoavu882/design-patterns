package com.malu.base.gift.consumer.web.rest.vm.mapper;

import com.malu.base.gift.admin.web.rest.vm.GiftProviderAdminVM;
import com.malu.base.gift.consumer.web.rest.vm.GiftProviderConsumerVM;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.service.dto.GiftProviderDTO;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link GiftProvider} and its DTO {@link GiftProviderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GiftProviderConsumerVMMapper extends EntityMapper<GiftProviderConsumerVM, GiftProvider> {
}
