package com.malu.base.gift.consumer.web.rest.vm.mapper;

import com.malu.base.gift.admin.web.rest.vm.GiftAdminVM;
import com.malu.base.gift.admin.web.rest.vm.mapper.GiftLangAdminVMMapper;
import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Gift} and its DTO {@link GiftConsumerVM}.
 */
@Mapper(componentModel = "spring", uses = { GiftProviderConsumerVMMapper.class })
public interface GiftConsumerVMMapper extends EntityMapper<GiftConsumerVM, Gift> {}
