package com.malu.base.gift.consumer.web.rest.vm.mapper;

import com.malu.base.gift.consumer.web.rest.vm.GiftSeasonConsumerVM;
import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link GiftSeason} and its DTO {@link GiftSeasonConsumerVM}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GiftSeasonConsumerVMMapper extends EntityMapper<GiftSeasonConsumerVM, GiftSeason> {}
