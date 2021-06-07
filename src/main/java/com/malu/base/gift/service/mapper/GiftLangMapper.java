package com.malu.base.gift.service.mapper;

import com.malu.base.gift.domain.*;
import com.malu.base.gift.service.dto.GiftLangDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GiftLang} and its DTO {@link GiftLangDTO}.
 */
@Mapper(componentModel = "spring", uses = { GiftMapper.class })
public interface GiftLangMapper extends EntityMapper<GiftLangDTO, GiftLang> {
    @Mapping(target = "gift", source = "gift", qualifiedByName = "id")
    GiftLangDTO toDto(GiftLang s);
}
