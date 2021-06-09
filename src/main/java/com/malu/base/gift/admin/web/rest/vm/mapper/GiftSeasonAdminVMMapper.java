package com.malu.base.gift.admin.web.rest.vm.mapper;

import com.malu.base.gift.admin.web.rest.vm.GiftSeasonAdminVM;
import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.service.mapper.EntityMapper;
import com.malu.base.gift.service.mapper.GiftProviderMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link GiftSeason} and its DTO {@link GiftSeasonAdminVM}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GiftSeasonAdminVMMapper extends EntityMapper<GiftSeasonAdminVM, GiftSeason> {
}
