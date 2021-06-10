package com.malu.base.gift.consumer.service.mapper;

import com.malu.base.gift.consumer.service.dto.GiftSeasonConsumerDTO;
import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link GiftSeason} and its DTO {@link GiftSeasonConsumerDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftProviderConsumerDTOMapper.class })
public interface GiftSeasonConsumerDTOMapper extends EntityMapper<GiftSeasonConsumerDTO, GiftSeason> {}
