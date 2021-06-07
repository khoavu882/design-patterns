package com.malu.base.gift.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiftProviderMapperTest {

    private GiftProviderMapper giftProviderMapper;

    @BeforeEach
    public void setUp() {
        giftProviderMapper = new GiftProviderMapperImpl();
    }
}
