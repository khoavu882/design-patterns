package com.malu.base.gift.consumer.web.rest.vm.mapper;

import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Gift} and its DTO {@link GiftConsumerVM}.
 */
//@Mapper(componentModel = "spring", uses = { GiftSeasonConsumerVMMapper.class, GiftLangConsumerVMMapper.class })
@Mapper(componentModel = "spring", uses = { /*GiftLangConsumerVMMapper.class*/ })
public interface GiftConsumerVMMapper extends EntityMapper<GiftConsumerVM, Gift> {}
