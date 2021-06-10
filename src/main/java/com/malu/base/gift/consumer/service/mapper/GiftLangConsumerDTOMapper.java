package com.malu.base.gift.consumer.service.mapper;

import com.malu.base.gift.consumer.service.dto.GiftLangConsumerDTO;
import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link GiftLang} and its DTO {@link GiftLangConsumerDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftConsumerDTOMapper.class })
public interface GiftLangConsumerDTOMapper extends EntityMapper<GiftLangConsumerDTO, GiftLang> {}
