package com.malu.base.gift.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiftLangMapperTest {

    private GiftLangMapper giftLangMapper;

    @BeforeEach
    public void setUp() {
        giftLangMapper = new GiftLangMapperImpl();
    }
}
