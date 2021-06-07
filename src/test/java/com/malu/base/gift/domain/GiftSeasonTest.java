package com.malu.base.gift.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.malu.base.gift.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GiftSeasonTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftSeason.class);
        GiftSeason giftSeason1 = new GiftSeason();
        giftSeason1.setId(1L);
        GiftSeason giftSeason2 = new GiftSeason();
        giftSeason2.setId(giftSeason1.getId());
        assertThat(giftSeason1).isEqualTo(giftSeason2);
        giftSeason2.setId(2L);
        assertThat(giftSeason1).isNotEqualTo(giftSeason2);
        giftSeason1.setId(null);
        assertThat(giftSeason1).isNotEqualTo(giftSeason2);
    }
}
