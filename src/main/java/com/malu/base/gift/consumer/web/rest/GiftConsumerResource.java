package com.malu.base.gift.consumer.web.rest;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.service.GiftConsumerService;
import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.dto.GiftDTO;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

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
     * {@code GET  } : get all the gifts.
     *
     * @param keyword the keyword for search.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gifts in body.
     */
    @GetMapping(value = { "/gifts", "/gifts/{keyword}" })
    public ResponseEntity<List<GiftConsumerVM>> getAllGifts(@PathVariable Optional<String> keyword, Pageable pageable) {
        log.debug("REST request to get a page of Gifts");
        Page<GiftConsumerVM> page = giftConsumerService.findAllWithFilterByConsumer(keyword.orElse(null), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gifts/details/:id} : get the "id" gift.
     *
     * @param id the id of the giftDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gifts/details/{id}")
    public ResponseEntity<GiftDTO> getGift(@PathVariable Long id) {
        log.debug("REST request to get Gift : {}", id);
        Optional<GiftDTO> giftDTO = giftConsumerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(giftDTO);
    }
}
