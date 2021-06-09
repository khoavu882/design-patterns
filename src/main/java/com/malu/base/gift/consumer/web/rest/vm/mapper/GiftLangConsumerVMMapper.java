package com.malu.base.gift.consumer.web.rest.vm.mapper;

import com.malu.base.gift.admin.web.rest.vm.GiftLangAdminVM;
import com.malu.base.gift.consumer.web.rest.vm.GiftLangConsumerVM;
import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link GiftLang} and its DTO {@link GiftLangConsumerVM}.
 */
@Mapper(componentModel = "spring")
public interface GiftLangConsumerVMMapper extends EntityMapper<GiftLangConsumerVM, GiftLang> {
}
