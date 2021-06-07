package com.malu.base.gift.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.malu.base.gift.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GiftLangTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftLang.class);
        GiftLang giftLang1 = new GiftLang();
        giftLang1.setId(1L);
        GiftLang giftLang2 = new GiftLang();
        giftLang2.setId(giftLang1.getId());
        assertThat(giftLang1).isEqualTo(giftLang2);
        giftLang2.setId(2L);
        assertThat(giftLang1).isNotEqualTo(giftLang2);
        giftLang1.setId(null);
        assertThat(giftLang1).isNotEqualTo(giftLang2);
    }
}
