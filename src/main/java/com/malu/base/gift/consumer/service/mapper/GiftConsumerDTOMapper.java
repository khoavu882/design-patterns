package com.malu.base.gift.consumer.service.mapper;

import com.malu.base.gift.consumer.service.dto.GiftConsumerDTO;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Gift} and its DTO {@link GiftConsumerDTO}.
 */
@Mapper(componentModel = "spring", uses = { /*GiftSeasonConsumerDTOMapper.class*/ })
public interface GiftConsumerDTOMapper extends EntityMapper<GiftConsumerDTO, Gift> {}
