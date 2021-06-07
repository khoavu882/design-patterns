package com.malu.base.gift.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.malu.base.gift.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GiftProviderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftProvider.class);
        GiftProvider giftProvider1 = new GiftProvider();
        giftProvider1.setId(1L);
        GiftProvider giftProvider2 = new GiftProvider();
        giftProvider2.setId(giftProvider1.getId());
        assertThat(giftProvider1).isEqualTo(giftProvider2);
        giftProvider2.setId(2L);
        assertThat(giftProvider1).isNotEqualTo(giftProvider2);
        giftProvider1.setId(null);
        assertThat(giftProvider1).isNotEqualTo(giftProvider2);
    }
}
