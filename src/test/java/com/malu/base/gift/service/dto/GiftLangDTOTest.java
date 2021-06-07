package com.malu.base.gift.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.malu.base.gift.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GiftLangDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftLangDTO.class);
        GiftLangDTO giftLangDTO1 = new GiftLangDTO();
        giftLangDTO1.setId(1L);
        GiftLangDTO giftLangDTO2 = new GiftLangDTO();
        assertThat(giftLangDTO1).isNotEqualTo(giftLangDTO2);
        giftLangDTO2.setId(giftLangDTO1.getId());
        assertThat(giftLangDTO1).isEqualTo(giftLangDTO2);
        giftLangDTO2.setId(2L);
        assertThat(giftLangDTO1).isNotEqualTo(giftLangDTO2);
        giftLangDTO1.setId(null);
        assertThat(giftLangDTO1).isNotEqualTo(giftLangDTO2);
    }
}
