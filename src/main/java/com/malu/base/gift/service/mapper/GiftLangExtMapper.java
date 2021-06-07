package com.malu.base.gift.service.mapper;

import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.service.dto.GiftLangDTO;
import com.malu.base.gift.service.dto.GiftLangExtDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link GiftLang} and its DTO {@link GiftLangDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftExtMapper.class })
public interface GiftLangExtMapper extends EntityMapper<GiftLangExtDTO, GiftLang> {
}
