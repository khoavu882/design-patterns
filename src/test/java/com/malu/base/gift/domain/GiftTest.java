package com.malu.base.gift.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.malu.base.gift.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GiftTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gift.class);
        Gift gift1 = new Gift();
        gift1.setId(1L);
        Gift gift2 = new Gift();
        gift2.setId(gift1.getId());
        assertThat(gift1).isEqualTo(gift2);
        gift2.setId(2L);
        assertThat(gift1).isNotEqualTo(gift2);
        gift1.setId(null);
        assertThat(gift1).isNotEqualTo(gift2);
    }
}
