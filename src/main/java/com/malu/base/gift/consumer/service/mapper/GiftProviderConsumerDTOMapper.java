package com.malu.base.gift.consumer.service.mapper;

import com.malu.base.gift.consumer.service.dto.GiftProviderConsumerDTO;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link GiftProvider} and its DTO {@link GiftProviderConsumerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GiftProviderConsumerDTOMapper extends EntityMapper<GiftProviderConsumerDTO, GiftProvider> {}
