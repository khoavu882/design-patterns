package com.malu.base.gift.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.malu.base.gift.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GiftSeasonDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftSeasonDTO.class);
        GiftSeasonDTO giftSeasonDTO1 = new GiftSeasonDTO();
        giftSeasonDTO1.setId(1L);
        GiftSeasonDTO giftSeasonDTO2 = new GiftSeasonDTO();
        assertThat(giftSeasonDTO1).isNotEqualTo(giftSeasonDTO2);
        giftSeasonDTO2.setId(giftSeasonDTO1.getId());
        assertThat(giftSeasonDTO1).isEqualTo(giftSeasonDTO2);
        giftSeasonDTO2.setId(2L);
        assertThat(giftSeasonDTO1).isNotEqualTo(giftSeasonDTO2);
        giftSeasonDTO1.setId(null);
        assertThat(giftSeasonDTO1).isNotEqualTo(giftSeasonDTO2);
    }
}
