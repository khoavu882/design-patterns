package com.malu.base.gift.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.malu.base.gift.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GiftProviderDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftProviderDTO.class);
        GiftProviderDTO giftProviderDTO1 = new GiftProviderDTO();
        giftProviderDTO1.setId(1L);
        GiftProviderDTO giftProviderDTO2 = new GiftProviderDTO();
        assertThat(giftProviderDTO1).isNotEqualTo(giftProviderDTO2);
        giftProviderDTO2.setId(giftProviderDTO1.getId());
        assertThat(giftProviderDTO1).isEqualTo(giftProviderDTO2);
        giftProviderDTO2.setId(2L);
        assertThat(giftProviderDTO1).isNotEqualTo(giftProviderDTO2);
        giftProviderDTO1.setId(null);
        assertThat(giftProviderDTO1).isNotEqualTo(giftProviderDTO2);
    }
}
