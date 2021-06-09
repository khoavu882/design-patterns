package com.malu.base.gift.consumer.web.rest;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.service.GiftConsumerService;
import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.domain.Gift;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link Gift}.
 */
@RestController
@RequestMapping("/api/v1/consumer")
public class GiftConsumerResource {

    private final Logger log = LoggerFactory.getLogger(GiftConsumerResource.class);

    private static final String ENTITY_NAME = "giftGift";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftConsumerService giftConsumerService;

    public GiftConsumerResource(@Qualifier(ApplicationConstant.CONSUMER) GiftConsumerService giftConsumerService) {
        this.giftConsumerService = giftConsumerService;
    }

    /**
     * {@code GET  /gifts/details/:hashCode} : get the "hashCode" gift.
     *
     * @param hashCode the id of the GiftAdminVM to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the GiftAdminVM, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gifts/details/{hashCode}")
    public ResponseEntity<GiftConsumerVM> getGiftByHashCode(@PathVariable String hashCode) {
        log.debug("REST request to get Gift : {}", hashCode);
        GiftConsumerVM giftConsumerVM = giftConsumerService.consumerFindOneByHashCode(hashCode);
        return ResponseEntity.ok().body(giftConsumerVM);
    }
}
